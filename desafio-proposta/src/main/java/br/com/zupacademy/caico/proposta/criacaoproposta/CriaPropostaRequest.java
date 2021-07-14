package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import br.com.zupacademy.caico.proposta.exception.Exceptions;
import br.com.zupacademy.caico.proposta.validators.Documento;


public class CriaPropostaRequest {

	@NotBlank
	@Documento(message = "O documento informado é inválido.")
	private String documento;

	@NotBlank @Email
	private String email;
	
	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;
	
	@NotNull @Positive
	private BigDecimal salario;

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	@Deprecated
	public CriaPropostaRequest() {
		
	}

	/**
	 * 
	 * @param documento
	 * @param email
	 * @param nome
	 * @param endereco
	 * @param salario
	 */
	
	public CriaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Propostas toModel(@Valid CriaPropostaRequest request) {
		Propostas proposta = new Propostas(this.documento, this.email, this.nome, this.endereco, salario);
		
		if(!documentoValido()) {
			throw new Exceptions("O documento informádo é inválido.");
		}
		
		return proposta;
	}
	
	public boolean documentoValido() {
		Assert.hasLength(documento,
				"O documento está vazio.");

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null)
				|| cnpjValidator.isValid(documento, null);
	}
	
	
	
}
