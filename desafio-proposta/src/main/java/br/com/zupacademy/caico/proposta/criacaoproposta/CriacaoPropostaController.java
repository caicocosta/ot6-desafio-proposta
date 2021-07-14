package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;

@RestController
public class CriacaoPropostaController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> criaProposta(@RequestBody @Valid CriaPropostaRequest request, UriComponentsBuilder uri){
		
		Propostas propostaExistente = propostaRepository.findByDocumento(request.getDocumento());
		
		if(propostaExistente != null) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta cadastrada para esse usuário");
		}
		
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
