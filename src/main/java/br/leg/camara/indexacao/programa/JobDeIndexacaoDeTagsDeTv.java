package br.leg.camara.indexacao.programa;

import br.leg.camara.indexacao.api.JobAgregadorDeDocumentos;

/**
 * Job que agrega todas as tags de tv, retornando isso em um único documento a ser indexado/atualizado
 */
public class JobDeIndexacaoDeTagsDeTv extends JobAgregadorDeDocumentos<JobDeListagemDeTagsTv> {

	@Override
	protected JobDeListagemDeTagsTv criarJobAlvo() {
		return new JobDeListagemDeTagsTv();
	}

	@Override
	protected String nomeDoCampoAgregado() {
		return JobDeListagemDeTagsTv.NOME_CAMPO;
	}

	@Override
	protected String nomeDoCampoComResultadoDaAgregacao() {
		return "tags";
	}

	@Override
	public String nome() {
		return "tags-tv";
	}

	@Override
	public String nomeDoIndice() {
		return JobDeIndexacaoDeTv.NOME_DO_INDICE;
	}

	@Override
	public long quantidadeDeDocumentos() {
		return getJobAlvo().quantidadeDeNoticiasComCampoAgregador();
	}
}