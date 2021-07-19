package br.com.zupacademy.caico.proposta.criarbiometria;

import java.util.Base64;

import javax.persistence.EntityManager;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;

public class BiometriaRequest {
	
	@Lob
	@NotBlank
	private String biometria;
	
	@Deprecated
	public BiometriaRequest() {
		
	}
	
	public BiometriaRequest(@NotBlank String biometria) {
		super();
		this.biometria = biometria;
	}

	public String getBiometria() {
		return biometria;
	}
	
	public Biometrias toModel(EntityManager entityManager, Long idCartao) {
		
		Cartoes cartao = entityManager.find(Cartoes.class, idCartao);
		
		if(cartao == null) {
			throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
		}
		
		this.biometria = Base64.getEncoder().encodeToString(biometria.getBytes());
		
		Biometrias biometrias = new Biometrias(cartao, this.biometria);
		
		entityManager.persist(biometrias);
		
		return biometrias;
	}

}
