package br.leg.camara.indexacao.programa;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import br.leg.camara.indexacao.programa.util.Campos;

public class JobDeIndexacaoDeTv extends JobSqlParametrizavelPrograma{

	static final String NOME_PREFIXO_PROPRIEDADE = "camaranews";
	static final String NOME_DO_INDICE = "programas-tv";

	@Override
	protected String sqlTodosDocumentos() {
		return criarSql("n.ID as id, n.post_title as titulo, n.post_excerpt as resumo, "
				+ "(SELECT ps.post_title FROM wp_posts ps where ps.ID = m.meta_value) as programa, "
	 			+ "concat_ws(IFNULL(n.post_content, ''),' ',IFNULL((SELECT m.meta_value FROM wp_postmeta m where m.meta_key = 'cd_rodape' and m.post_id = n.ID),'')) as materia, " +
				"n.post_date as data, YEAR(n.post_date) as ano, 'tv-camara' as veiculo, n.post_date as dataOrdenacao, "
				+ "(SELECT m.meta_value FROM wp_postmeta m where m.meta_key = '"+Campos.CD_RODAPE+"' and m.post_id = n.ID) as rodape ", 
				"order by id asc");
	}

	@Override
	protected String sqlQuantidadeDeDocumentos() {
		return criarSql("count(*)", null);
	}

	// Busca materias da agencia e materias da tv
	private String criarSql(String colunasSelect, String orderBy) {
		return "select " + colunasSelect +
				" from wp_posts n"+
				" left join wp_postmeta m on (m.post_id = n.id and m.meta_key = 'cd_programaPrincipal')" +
				" where n.post_type = 'edicao_programa_tv' " + 
				" and n.post_status = 'publish' " +
				seIdNoticiaInformado(" and n.ID = ? ", "") +
				(orderBy != null ? orderBy : "");
	}
	@Override
	protected void adaptarDocumentoQueSeraIndexado(Map<String, Object> documento) {
		//Retira as tags HTML do campo noticia
		String materia = (String)documento.get("materia");
		String materiaSemScripts = Jsoup.clean(materia, Whitelist.basic());
		String materiaSemTags = Jsoup.parse(materiaSemScripts).text();				
		documento.replace("materia", materiaSemTags);
	}	

	@Override
	public String nome() {
		return "programas-tv";
	}

	@Override
	public String nomeDoIndice() {
		return NOME_DO_INDICE;
	}

	@Override
	protected String nomeJobPrefixoPropriedade() {
		return NOME_PREFIXO_PROPRIEDADE;
	}
}
