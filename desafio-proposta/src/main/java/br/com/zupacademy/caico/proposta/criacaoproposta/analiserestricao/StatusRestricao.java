package br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao;

public enum StatusRestricao {

	COM_RESTRICAO,
	SEM_RESTRICAO;
	
	public StatusProposta getStatusProposta() {
		if(this.equals(SEM_RESTRICAO)) {
			return StatusProposta.ELEGIVEL;
		}
		return StatusProposta.NAO_ELEGIVEL;
	}
}
