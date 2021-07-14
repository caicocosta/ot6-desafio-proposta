package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AnaliseSolicitacao {

	public RetornoAnaliseProposta getRestricao(Propostas proposta) {

		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("documento", proposta.getDocumento(),
                "nome", proposta.getNome(),
                "idProposta", String.valueOf(proposta.getId()));
		
		ResponseEntity<RetornoAnaliseProposta> retorno = restTemplate.postForEntity("http://localhost:9999/api/solicitacao", request, RetornoAnaliseProposta.class);	
		
		return retorno.getBody();
	}

	
}
