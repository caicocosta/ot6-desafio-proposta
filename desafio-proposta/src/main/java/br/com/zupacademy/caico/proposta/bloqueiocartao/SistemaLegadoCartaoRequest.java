package br.com.zupacademy.caico.proposta.bloqueiocartao;

public class SistemaLegadoCartaoRequest {
	
	private String sistemaResponsavel;

	public SistemaLegadoCartaoRequest(String sistemaResponsavel) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
	}
	
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

}
