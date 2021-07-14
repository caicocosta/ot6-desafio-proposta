package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Propostas {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank
	@Column(nullable = false)
	private String documento;

	@NotBlank @Email
	@Column(nullable = false)
	private String email;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@Column(nullable = false)
	private String endereco;
	
	@NotNull @Positive
	@Column(nullable = false)
	private BigDecimal salario;

	public Propostas(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Integer getId() {
		return id;
	}
	
	
}
