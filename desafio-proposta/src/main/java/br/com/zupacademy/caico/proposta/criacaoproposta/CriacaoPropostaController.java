package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.caico.proposta.clientesfeign.SolicitacaoClientFeign;
import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.AnalisePropostaRequest;
import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.RetornoAnaliseProposta;
import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.StatusProposta;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;
import feign.FeignException;

@RestController
public class CriacaoPropostaController {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private SolicitacaoClientFeign solicitacaoCliente;
	
	@PostMapping("/propostas")
	@Transactional
	public ResponseEntity<?> criaProposta(@RequestBody @Valid CriaPropostaRequest request, UriComponentsBuilder uri){
		
		Propostas propostaExistente = propostaRepository.findByDocumento(request.getDocumento());
		
		if(propostaExistente != null) {
			throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe uma proposta cadastrada para esse usuário");
		} 
		
		Propostas proposta = request.toModel(request);
		
		propostaRepository.save(proposta);
		
		processaSolicitacao(proposta);
		
		URI retornoURI = formataURI(uri, proposta);
		
		return ResponseEntity.created(retornoURI).build();
	}
	
	

	private void processaSolicitacao(Propostas proposta) {
		
		AnalisePropostaRequest analiseRequest = new AnalisePropostaRequest(
				proposta.getDocumento(),
				proposta.getNome(),
				String.valueOf(proposta.getId())
				);
		try {
			RetornoAnaliseProposta retornoProposta = solicitacaoCliente.getRestricao(analiseRequest);
			proposta.setStatus(retornoProposta.getResultadoSolicitacao().getStatusProposta());
			propostaRepository.save(proposta);
		} catch (FeignException e) {
			if (e.status() == 422) {
				proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
				propostaRepository.save(proposta);
				throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Solicitante com restrição");	
			}
			throw new ApiErroException(HttpStatus.BAD_GATEWAY, "Houve um problema com a conexão ao servidor.");
		}
		
	}

	
	
	private URI formataURI(UriComponentsBuilder uri, Propostas proposta) {
		URI uriRetorno = uri.path("/nova-proposta/{id}").buildAndExpand(proposta.getId().toString()).toUri();
		return uriRetorno;
				
	}
	
}
