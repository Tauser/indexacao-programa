package br.leg.camara.indexacao.programa;

import br.leg.camara.indexacao.api.JobAgregadorDeDocumentos;

/**
 * Job que agrega todas as tags de radio, retornando isso em um único documento a ser indexado/atualizado
 */
public class JobDeIndexacaoDeTagsDeRadio extends JobAgregadorDeDocumentos<JobDeListagemDeTagsRadio> {

	@Override
	protected JobDeListagemDeTagsRadio criarJobAlvo() {
		return new JobDeListagemDeTagsRadio();
	}

	@Override
	protected String nomeDoCampoAgregado() {
		return JobDeListagemDeTagsRadio.NOME_CAMPO;
	}

	@Override
	protected String nomeDoCampoComResultadoDaAgregacao() {
		return "tags";
	}

	@Override
	public String nome() {
		return "tags-radio";
	}

	@Override
	public String nomeDoIndice() {
		return JobDeIndexacaoDeRadio.NOME_DO_INDICE;
	}

	@Override
	public long quantidadeDeDocumentos() {
		return getJobAlvo().quantidadeDeNoticiasComCampoAgregador();
	}
}