package br.com.zupacademy.caico.proposta.criarbiometria;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CriaBiometriaController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping("/cartoes/biometria/{idCartao}")
	@Transactional
	public ResponseEntity<?> criaBiometria(
			@RequestBody @Valid BiometriaRequest biometriaRequest,
			@PathVariable Long idCartao,
			UriComponentsBuilder uri){
		Biometrias biometria = biometriaRequest.toModel(entityManager, idCartao);
		
		URI uriBometria = uri.path("nova-biometria/{id}").buildAndExpand(biometria.getId()).toUri();
		
		return ResponseEntity.created(uriBometria).build();
	}
	
}
