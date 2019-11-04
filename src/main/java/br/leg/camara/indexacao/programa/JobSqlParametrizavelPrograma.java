package br.leg.camara.indexacao.programa;

import br.leg.camara.indexacao.adaptadores.sql.JobDeIndexacaoSql;
import br.leg.camara.indexacao.api.ParametrosExecucao;

abstract class JobSqlParametrizavelPrograma extends JobDeIndexacaoSql {

	static final String PARAMETRO_ID_NOTICIA = "idPrograma";

	private String idPrograma;

	@Override
	protected void receberParametros(ParametrosExecucao parametros) {
		idPrograma = parametros.valorSimples(PARAMETRO_ID_NOTICIA, null);
	}

	@Override
	protected Object[] argumentosDoSqlQuantidadeDeDocumentos() {
		return seIdNoticiaInformado(new Object[] {idPrograma}, new Object[]{});
	}

	@Override
	protected Object[] argumentosDoSqlTodosDocumentos() {
		return argumentosDoSqlQuantidadeDeDocumentos();
	}

	<T> T seIdNoticiaInformado(T valorCasoIdExista, T valorCasoIdAusente) {
		return idPrograma != null ? valorCasoIdExista : valorCasoIdAusente;
	}

	@Override
	public void encerrar() {
		super.encerrar();
		idPrograma = null;
	}
}
