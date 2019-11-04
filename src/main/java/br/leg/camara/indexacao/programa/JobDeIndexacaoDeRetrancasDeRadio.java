package br.leg.camara.indexacao.programa;

import br.leg.camara.indexacao.api.JobAgregadorDeDocumentos;

/**
 * Job que agrega todas as retrancas de radio, retornando isso em um único documento a ser indexado/atualizado
 */
public class JobDeIndexacaoDeRetrancasDeRadio extends JobAgregadorDeDocumentos<JobDeListagemDeRetrancasRadio> {

	@Override
	protected JobDeListagemDeRetrancasRadio criarJobAlvo() {
		return new JobDeListagemDeRetrancasRadio();
	}

	@Override
	protected String nomeDoCampoAgregado() {
		return JobDeListagemDeRetrancasRadio.NOME_CAMPO_RETRANCA;
	}

	@Override
	protected String nomeDoCampoComResultadoDaAgregacao() {
		return "retrancas";
	}

	@Override
	public String nome() {
		return "retrancas-radio";
	}

	@Override
	public String nomeDoIndice() {
		return JobDeIndexacaoDeRadio.NOME_DO_INDICE;
	}

	@Override
	public long quantidadeDeDocumentos() {
		return getJobAlvo().quantidadeDeNoticiasComRetranca();
	}
}
