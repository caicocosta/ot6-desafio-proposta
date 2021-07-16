package br.com.zupacademy.caico.proposta.acompanhamentoproposta;

import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.StatusProposta;

public class PropostaResponse {

	private Long id;
	private String nome;
	private StatusProposta status;
	
	@Deprecated
	public PropostaResponse() {
		
	}
	
	public PropostaResponse(Long id, String nome, StatusProposta status) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public StatusProposta getStatus() {
		return status;
	}
	
}
