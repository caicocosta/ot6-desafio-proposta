package br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "solicitacaoProposta", url = "${consulta-dados.url}")
public interface SolicitacaoClient {

	@RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
	RetornoAnaliseProposta getRestricao(@RequestBody AnalisePropostaRequest proposta);
}
