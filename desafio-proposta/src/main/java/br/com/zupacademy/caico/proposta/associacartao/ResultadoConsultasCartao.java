package br.com.zupacademy.caico.proposta.associacartao;

import br.com.zupacademy.caico.proposta.bloqueiocartao.StatusCartao;

public class ResultadoConsultasCartao {

	private String id;
	private StatusCartao resultado;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StatusCartao getResultado() {
		return resultado;
	}
	
	public void setResultado(StatusCartao resultado) {
		this.resultado = resultado;
	}
}
