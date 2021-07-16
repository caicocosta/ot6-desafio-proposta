package br.com.zupacademy.caico.proposta.acompanhamentoproposta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.caico.proposta.criacaoproposta.PropostaRepository;
import br.com.zupacademy.caico.proposta.criacaoproposta.Propostas;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;

@RestController
public class AcompanhamentoPropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@GetMapping("/propostas/acompanhamento/{id}")
	public ResponseEntity<PropostaResponse> acompanhamento(@PathVariable Long id){
		
		Propostas proposta = propostaRepository.getById(id);
		
		if(proposta == null) {
			throw new ApiErroException(HttpStatus.NOT_FOUND, "Proposta inválida");
		}
		
		PropostaResponse response = new PropostaResponse(
				proposta.getId(), 
				proposta.getNome(), 
				proposta.getStatus());		
		
		return ResponseEntity.ok(response);
	}
}
