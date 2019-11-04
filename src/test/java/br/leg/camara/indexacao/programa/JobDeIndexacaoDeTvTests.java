package br.leg.camara.indexacao.programa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.leg.camara.indexacao.api.Configuracoes;
import br.leg.camara.indexacao.api.ParametrosExecucao;

public class JobDeIndexacaoDeTvTests extends TesteDeIntegracaoComBancoH2 {
private static final String PREFIXO_ARQUIVO_H2 = "tv";
	
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
	
	@Test
	public void quantidadeDeDocumentosDeveRetornarNumeroCorretoDeRegistros() {
		JobDeIndexacaoDeTv job = criarJobConfigurado();
		assertEquals(3, job.quantidadeDeDocumentos());
	}
	
	@Test
	public void proximoDocumentoPrimeiraChamada() {
		JobDeIndexacaoDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		Map<String, ?> documento = job.proximoDocumento();

		assertNotNull(documento);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(4, documento.get("ID"));
		assertEquals("Titulo teste 4", documento.get("TITULO"));
		assertEquals("Conteudo teste edicao_programa_tv 3 Materia 4 Rodape", documento.get("MATERIA"));
	}
	
	@Test
	public void programaPrimeiraChamada() {
		JobDeIndexacaoDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		Map<String, ?> documento = job.proximoDocumento();

		assertNotNull(documento);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(4, documento.get("ID"));
		assertEquals("Câmara Agora", documento.get("PROGRAMA"));
	}
	
	@Test
	public void proximoDocumentoUltimaCahamada() {
		JobDeIndexacaoDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		job.proximoDocumento();
		job.proximoDocumento();
		Map<String, ?> documento = job.proximoDocumento();

		assertNotNull(documento);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(6, documento.get("ID"));
		assertEquals("Titulo teste 6", documento.get("TITULO"));
		assertEquals("Conteudo teste edicao_programa_tv 6 Materia 6 Rodape", documento.get("MATERIA"));
	}

	@Test
	public void programaUltimaCahamada() {
		JobDeIndexacaoDeTv job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		job.proximoDocumento();
		job.proximoDocumento();
		Map<String, ?> documento = job.proximoDocumento();

		assertNotNull(documento);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(6, documento.get("ID"));
		assertEquals("Memória Política", documento.get("PROGRAMA"));
	}	
	
	private JobDeIndexacaoDeTv criarJobConfigurado() {
		Configuracoes configuracoes = criarConfiguracoesBancoMemoria(PREFIXO_ARQUIVO_H2);
		JobDeIndexacaoDeTv job = new JobDeIndexacaoDeTv();
		job.configurar(configuracoes);
		return job;
	}


}
