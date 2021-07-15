package br.com.zupacademy.caico.proposta.clientesfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.AnalisePropostaRequest;
import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.RetornoAnaliseProposta;
import br.com.zupacademy.caico.proposta.health.RetornoHealthAPisExternas;

@FeignClient(name = "solicitacaoProposta", url = "${consulta-dados.url}")
public interface SolicitacaoClientFeign {

	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
	RetornoAnaliseProposta getRestricao(@RequestBody AnalisePropostaRequest proposta);
	
	@RequestMapping(method = RequestMethod.GET, value = "/actuator/health/")
	RetornoHealthAPisExternas getStatusAPI();
}
