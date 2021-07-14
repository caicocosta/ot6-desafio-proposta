package br.com.zupacademy.caico.proposta.criacaoProposta;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.zupacademy.caico.proposta.criacaoproposta.CriaPropostaRequest;
import br.com.zupacademy.caico.proposta.criacaoproposta.Propostas;

public class testaCadastroProposta {
	
	@Test
	public void deveRetornarUmaProposta() {

		CriaPropostaRequest request = new CriaPropostaRequest(
				"09793051604", 
				"caico@gmail.com", 
				"caico", 
				"rua teste", 
				new BigDecimal(1000));
		
		Propostas proposta = request.toModel(request);
		
		Assertions.assertNotNull(proposta);
		
	}

}
