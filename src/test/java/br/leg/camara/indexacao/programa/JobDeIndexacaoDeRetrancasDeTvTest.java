package br.leg.camara.indexacao.programa;

import static br.leg.camara.indexacao.programa.JobSqlParametrizavelPrograma.PARAMETRO_ID_NOTICIA;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.leg.camara.indexacao.api.Configuracoes;
import br.leg.camara.indexacao.api.ParametrosExecucao;

public class JobDeIndexacaoDeRetrancasDeTvTest extends TesteDeIntegracaoComBancoH2 {

	private static final String PREFIXO_ARQUIVO_H2 = "retrancas2";

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

	private JobDeIndexacaoDeRetrancasDeTv criarJobConfigurado() {
		Configuracoes configuracoes = criarConfiguracoesBancoMemoria(PREFIXO_ARQUIVO_H2);
		JobDeIndexacaoDeRetrancasDeTv job = new JobDeIndexacaoDeRetrancasDeTv();
		job.setNomesDeCamposEmCaixaAlta(true);
		job.configurar(configuracoes);
		return job;
	}

	@Test
	public void quantidadeDeDocumentosDeveRetornarNumeroDeNoticiasComRetranca() {
		JobDeIndexacaoDeRetrancasDeTv job = criarJobConfigurado();
		assertEquals(2, job.quantidadeDeDocumentos());
	}

	@Test
	public void proximoDocumentoPrimeiraChamada() {
		JobDeIndexacaoDeRetrancasDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		Map<String, ?> primeiro = job.proximoDocumento();

		assertNotNull(primeiro);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(4, primeiro.get("ID"));
		assertEquals(singletonList("Trabalho"), primeiro.get("RETRANCAS"));
	}

	@Test
	public void proximoDocumentoSegundaChamada() {
		JobDeIndexacaoDeRetrancasDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		job.proximoDocumento();
		Map<String, ?> segundo = job.proximoDocumento();

		assertNotNull(segundo);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(5, segundo.get("ID"));
		assertEquals(singletonList("Economia"), segundo.get("RETRANCAS"));
	}


	@Test
	public void execucaoParametrizadaDeveRetornarRetrancasDeUmaNoticiaSomente() {
		JobDeIndexacaoDeRetrancasDeTv job = criarJobConfigurado();
		ParametrosExecucao parametros = ParametrosExecucao.comValorSimples(PARAMETRO_ID_NOTICIA, "5");
		job.iniciar(parametros);

		assertEquals(1, job.quantidadeDeDocumentos());

		Map<String, ?> doc = job.proximoDocumento();
		assertEquals(5, doc.get("ID"));
		assertEquals(singletonList("Economia"), doc.get("RETRANCAS"));
	}

	@Test
	public void execucaoParametrizadaComIdInexistenteDeveRetornarZeroDocumentos() {
		ParametrosExecucao parametros = ParametrosExecucao.comValorSimples(PARAMETRO_ID_NOTICIA, "999");
		JobDeIndexacaoDeRetrancasDeTv job = criarJobConfigurado();
		job.iniciar(parametros);

		assertEquals(0, job.quantidadeDeDocumentos());
	}
}