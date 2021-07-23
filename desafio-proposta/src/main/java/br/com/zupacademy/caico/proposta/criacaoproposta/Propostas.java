package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.math.BigDecimal;
import java.util.stream.LongStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.StatusProposta;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Propostas {

	@Id
	@GeneratedValue
	private Long id;
	
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
	
	@NotNull
	private StatusProposta status = StatusProposta.AGUARDANDO_ANALISE;
	
	@Deprecated
	public Propostas() {
		
	}

	public Propostas(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setStatus(StatusProposta status) {
		this.status = status;
	}
	
	public StatusProposta getStatus() {
		return status;
	}
	
}
