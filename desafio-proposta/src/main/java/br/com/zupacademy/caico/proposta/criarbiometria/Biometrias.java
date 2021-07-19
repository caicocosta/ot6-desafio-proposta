package br.com.zupacademy.caico.proposta.criarbiometria;

import java.time.LocalDateTime;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;

@Entity
public class Biometrias {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Cartoes cartao;
	
	@NotBlank
	@Column()
	@Lob
	private String biometria;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;

	@Deprecated
	public Biometrias() {
		
	}
	
	public Biometrias(Cartoes cartao, String biometria) {
		super();
		this.cartao = cartao;
		this.biometria = biometria;
	}
	
	public Long getId() {
		return id;
	}
	
}
