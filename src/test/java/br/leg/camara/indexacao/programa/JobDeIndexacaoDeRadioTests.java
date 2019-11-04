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

public class JobDeIndexacaoDeRadioTests extends TesteDeIntegracaoComBancoH2 {
	private static final String PREFIXO_ARQUIVO_H2 = "radio";
	
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
		JobDeIndexacaoDeRadio job = criarJobConfigurado();
		assertEquals(3, job.quantidadeDeDocumentos());
	}
	
	@Test
	public void proximoDocumentoPrimeiraChamada() {
		JobDeIndexacaoDeRadio job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		Map<String, ?> documento = job.proximoDocumento();

		assertNotNull(documento);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(1, documento.get("ID"));
		assertEquals("0:35", documento.get("tamanho"));
		assertEquals("Titulo teste 1", documento.get("TITULO"));
		assertEquals("Câmara Aberta", documento.get("PROGRAMA"));
		assertEquals("Conteudo teste edicao_programa_radi 1 Materia 1 Rodape", documento.get("MATERIA"));
	}
	
	@Test
	public void proximoDocumentoUltimaCahamada() {
		JobDeIndexacaoDeRadio job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		job.proximoDocumento();
		job.proximoDocumento();
		Map<String, ?> documento = job.proximoDocumento();

		assertNotNull(documento);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(3, documento.get("ID"));
		assertEquals("Titulo teste 3", documento.get("TITULO"));
		assertEquals("Conteudo teste edicao_programa_radi 3 Materia 3 Rodape", documento.get("MATERIA"));
	}
	
	@Test
	public void programaUltimaCahamada() {
		JobDeIndexacaoDeRadio job = criarJobConfigurado();

		job.iniciar(ParametrosExecucao.vazio());
		job.proximoDocumento();
		job.proximoDocumento();
		Map<String, ?> documento = job.proximoDocumento();

		assertNotNull(documento);
		//como o H2 retorna o nome das colunas sempre em Maiúsculas, estamos usando os nomes em maiúsculas
		assertEquals(3, documento.get("ID"));
		assertEquals("A Voz do Brasil", documento.get("PROGRAMA"));
	}


	
	private JobDeIndexacaoDeRadio criarJobConfigurado() {
		Configuracoes configuracoes = criarConfiguracoesBancoMemoria(PREFIXO_ARQUIVO_H2);
		JobDeIndexacaoDeRadio job = new JobDeIndexacaoDeRadio();
		job.configurar(configuracoes);
		return job;
	}


}
