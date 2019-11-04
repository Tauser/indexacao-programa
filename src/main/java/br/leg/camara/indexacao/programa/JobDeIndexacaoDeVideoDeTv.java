package br.leg.camara.indexacao.programa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.github.ooxi.phparser.SerializedPhpParserException;

import br.leg.camara.indexacao.api.Configuracoes;
import br.leg.camara.indexacao.api.JobAgregadorDeDocumentos;
import br.leg.camara.indexacao.api.ParametrosExecucao;
import br.leg.camara.indexacao.programa.util.Campos;
import br.leg.camara.indexacao.programa.util.Util;

/**
 * Job que agrega todas os videos de tv, retornando isso em um único
 * documento a ser indexado/atualizado
 */
public class JobDeIndexacaoDeVideoDeTv extends JobAgregadorDeDocumentos<JobDeListagemDeVideoTv> {

	private JdbcTemplate jdbcTemplate;
	private Util util = new Util();
	List<DTOMidia> videosWP;

	@Override
	public void configurar(Configuracoes configuracoes) {
		super.configurar(configuracoes);
		DataSource datasource = criarDatasource(configuracoes);
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	private DataSource criarDatasource(Configuracoes configuracoes) {
		String prefixoPropriedades = "jobs." + JobDeIndexacaoDeTv.NOME_PREFIXO_PROPRIEDADE + ".datasource.";
		return criarDatasource(configuracoes, prefixoPropriedades);
	}

	private DataSource criarDatasource(Configuracoes configuracoes, String prefixoPropriedades) {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(configuracoes.getPropriedadeObrigatoria(prefixoPropriedades + "url"));
		ds.setDriverClassName(configuracoes.getPropriedadeObrigatoria(prefixoPropriedades + "driver"));
		ds.setUsername(configuracoes.getPropriedadeObrigatoria(prefixoPropriedades + "usuario"));
		ds.setPassword(configuracoes.getPropriedadeObrigatoria(prefixoPropriedades + "senha"));
		return ds;
	}

	private String sqlTodosVideos() {
		// @formatter:off
		return "select distinct(r.id), r.post_title titulo, r.guid url, sm.meta_value meta, (select pm2.meta_value from wp_postmeta pm2 where pm2.post_id = r.id and pm2.meta_key = '"+Campos.CD_MIDIA_URLEXTERNA+"') as url2" 
				+ " from wp_posts p "
				+ " inner join wp_postmeta m on m.post_id = p.id and m.meta_key = '"+Campos.CD_POSTVIDEO+"' "
				+ " inner join wp_posts r on m.meta_value = r.id"
				+ " left join wp_postmeta sm on sm.meta_key = '"+Campos.ATTACHMENT_METADATA+"' and sm.post_id = r.id"
				+ " where p.post_status = 'publish' and p.post_type = 'edicao_programa_tv' ";
		// @formatter:on
	}

	@Override
	public void iniciar(ParametrosExecucao parametros) {
		super.iniciar(parametros);
		//videosWP = jdbcTemplate.query(sqlTodosVideos(), new BeanPropertyRowMapper<DTOMidia>(DTOMidia.class));
		videosWP = jdbcTemplate.query(sqlTodosVideos(), new RowMapper<DTOMidia>() {

			@Override
			public DTOMidia mapRow(ResultSet rs, int rowNum) throws SQLException {
				DTOMidia dto = new DTOMidia();
				
				dto.setId(rs.getInt("id"));
				dto.setMeta(rs.getString("meta"));
				dto.setTitulo(rs.getString("titulo"));
				
				String url = rs.getString("url");
				String url2 = rs.getString("url2");
				
				if (url == null || url == "" || url.endsWith("/")) {
					dto.setUrl(url2);
				} else {
					dto.setUrl(url);
				}
		
				return dto;
			}
			
		});
	}

	public Map<Integer, DTOMidia> converterListParaMap(List<DTOMidia> list) {
		Map<Integer, DTOMidia> map = list.stream().collect(Collectors.toMap(DTOMidia::getId, valor -> valor));
		return map;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void adaptarDocumentoQueSeraIndexado(Map<String, Object> documento) {
		String nomeCampoNormalizado = normalizarNomeCampo(nomeDoCampoComResultadoDaAgregacao());
		List<Integer> idsVideos = (List<Integer>) documento.get(nomeCampoNormalizado);
		List<Map<String, String>> videos = new ArrayList<Map<String, String>>();
		
		// @formatter:off		
		idsVideos.forEach(id -> {
			videosWP.forEach(audio -> {
				if (id.equals(audio.getId())) {
					Map<String, String> jsonImagem = new HashMap<>();
					jsonImagem.put(normalizarNomeCampo("url"), audio.getUrl());
					jsonImagem.put(normalizarNomeCampo("titulo"), audio.getTitulo());
					try {
						if(audio.getMeta() != null){
							jsonImagem.put(normalizarNomeCampo("tamanho"), util.unserializedWordpress(audio.getMeta().toString()).get("length_formatted").toString());	
						}
					} catch (SerializedPhpParserException e) {
						e.printStackTrace();
						throw new IllegalStateException("Erro ao unserializar meta dados: ", e);
					}
					videos.add(jsonImagem);
				}
			});
		});
		// @formatter:on
		documento.replace(nomeCampoNormalizado, videos);
	}

	@Override
	protected JobDeListagemDeVideoTv criarJobAlvo() {
		return new JobDeListagemDeVideoTv();
	}

	@Override
	protected String nomeDoCampoAgregado() {
		return JobDeListagemDeVideoTv.NOME_CAMPO;
	}

	@Override
	protected String nomeDoCampoComResultadoDaAgregacao() {
		return "videos";
	}

	@Override
	public String nome() {
		return "video-tv";
	}

	@Override
	public String nomeDoIndice() {
		return JobDeIndexacaoDeTv.NOME_DO_INDICE;
	}

	@Override
	public long quantidadeDeDocumentos() {
		return getJobAlvo().quantidadeDeNoticiasComAudio();
	}

	@Override
	public void encerrar() {
		super.encerrar();
		videosWP.clear();
	}

}