package br.leg.camara.indexacao.programa;

import br.leg.camara.indexacao.api.JobAgregadorDeDocumentos;

/**
 * Job que agrega todas as retrancas de tv, retornando isso em um único documento a ser indexado/atualizado
 */
public class JobDeIndexacaoDeRetrancasDeTv extends JobAgregadorDeDocumentos<JobDeListagemDeRetrancasTv> {

	@Override
	protected JobDeListagemDeRetrancasTv criarJobAlvo() {
		return new JobDeListagemDeRetrancasTv();
	}

	@Override
	protected String nomeDoCampoAgregado() {
		return JobDeListagemDeRetrancasTv.NOME_CAMPO_RETRANCA;
	}

	@Override
	protected String nomeDoCampoComResultadoDaAgregacao() {
		return "retrancas";
	}

	@Override
	public String nome() {
		return "retrancas-tv";
	}

	@Override
	public String nomeDoIndice() {
		return JobDeIndexacaoDeTv.NOME_DO_INDICE;
	}

	@Override
	public long quantidadeDeDocumentos() {
		return getJobAlvo().quantidadeDeNoticiasComRetranca();
	}
}
