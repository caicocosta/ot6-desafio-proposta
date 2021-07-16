package br.com.zupacademy.caico.proposta.associacartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.caico.proposta.criacaoproposta.Propostas;

@Entity
public class Cartoes {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String NumCartao;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotBlank
	private String titular;

	@NotNull
	private Integer limite;
	private Boolean renegociacao;
	
	@NotNull
	@ManyToOne
	private Propostas proposta;

	public Cartoes(@NotBlank String numCartao, @NotNull LocalDateTime emitidoEm, @NotBlank String titular,
			@NotNull Integer limite, Boolean renegociacao, @NotNull Propostas proposta) {
		super();
		NumCartao = numCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getNumCartao() {
		return NumCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Integer getLimite() {
		return limite;
	}

	public Boolean getRenegociacao() {
		return renegociacao;
	}

	public Propostas getProposta() {
		return proposta;
	}
	
}
