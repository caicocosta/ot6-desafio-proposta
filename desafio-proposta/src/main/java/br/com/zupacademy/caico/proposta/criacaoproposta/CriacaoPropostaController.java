package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CriacaoPropostaController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> criaProposta(@RequestBody @Valid CriaPropostaRequest request, UriComponentsBuilder uri){
		
		Propostas proposta = request.toModel(request);
		
		entityManager.persist(proposta);
		
		URI retornoURI = formataURI(uri, proposta);
		
		return ResponseEntity.created(retornoURI).build();
	}

	private URI formataURI(UriComponentsBuilder uri, Propostas proposta) {
		URI uriRetorno = uri.path("/nova-proposta/{id}").buildAndExpand(proposta.getId().toString()).toUri();
		return uriRetorno;
				
	}
	
}
