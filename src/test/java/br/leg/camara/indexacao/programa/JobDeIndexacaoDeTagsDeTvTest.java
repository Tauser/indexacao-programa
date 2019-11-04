package br.leg.camara.indexacao.programa;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.leg.camara.indexacao.api.Configuracoes;
import br.leg.camara.indexacao.api.ParametrosExecucao;
import static br.leg.camara.indexacao.programa.JobSqlParametrizavelPrograma.PARAMETRO_ID_NOTICIA;

public class JobDeIndexacaoDeTagsDeTvTest extends TesteDeIntegracaoComBancoH2 {

	private static final String PREFIXO_ARQUIVO_H2 = "tags2";

	@BeforeClass
	public static void criarBanco() throws Exception {
		criarBancoDeDados(PREFIXO_ARQUIVO_H2);
	}

	@AfterClass
	public static void apagarBanco() throws IOException {
		removerArquivosTemporariosDoH2(PREFIXO_ARQUIVO_H2);
	}

	@Test
	public void configurarNaoDeveLancarExcecao() {
		criarJobConfigurado();
	}

	private JobDeIndexacaoDeTagsDeTv criarJobConfigurado() {
		Configuracoes configuracoes = criarConfiguracoesBancoMemoria(PREFIXO_ARQUIVO_H2);
		JobDeIndexacaoDeTagsDeTv job = new JobDeIndexacaoDeTagsDeTv();
		job.setNomesDeCamposEmCaixaAlta(true);
		job.configurar(configuracoes);
		return job;
	}

	@Test
	public void quantidadeDeDocumentosDeveRetornarNumeroDeNoticiasComRetranca() {
		JobDeIndexacaoDeTagsDeTv job = criarJobConfigurado();
		assertEquals(2, job.quantidadeDeDocumentos());
	}

	@Test
	public void proximoDocumentoPrimeiraChamada() {
		JobDeIndexacaoDeTagsDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		Map<String, ?> primeiro = job.proximoDocumento();

		assertNotNull(primeiro);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(4, primeiro.get("ID"));
		assertEquals(Arrays.asList("Meio ambiente", "quebra de decoro"), primeiro.get("TAGS"));
	}


	@Test
	public void proximoDocumentoTerceiraChamada() {
		JobDeIndexacaoDeTagsDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		job.proximoDocumento();
		Map<String, ?> terceiro = job.proximoDocumento();

		assertNotNull(terceiro);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(6, terceiro.get("ID"));
		assertEquals(singletonList("quebra de decoro"), terceiro.get("TAGS"));
	}

	@Test
	public void execucaoParametrizadaDeveRetornarTagsDeUmaNoticiaSomente() {
		JobDeIndexacaoDeTagsDeTv job = criarJobConfigurado();
		ParametrosExecucao parametros = ParametrosExecucao.comValorSimples(PARAMETRO_ID_NOTICIA, "4");
		job.iniciar(parametros);

		assertEquals(1, job.quantidadeDeDocumentos());

		Map<String, ?> doc = job.proximoDocumento();
		assertEquals(4, doc.get("ID"));
		assertEquals(Arrays.asList("Meio ambiente", "quebra de decoro"), doc.get("TAGS"));
	}

	@Test
	public void execucaoParametrizadaComIdInexistenteDeveRetornarZeroDocumentos() {
		ParametrosExecucao parametros = ParametrosExecucao.comValorSimples(PARAMETRO_ID_NOTICIA, "999");
		JobDeIndexacaoDeTagsDeTv job = criarJobConfigurado();
		job.iniciar(parametros);

		assertEquals(0, job.quantidadeDeDocumentos());
	}
}