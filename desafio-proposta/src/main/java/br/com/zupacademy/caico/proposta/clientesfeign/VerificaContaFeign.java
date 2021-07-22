package br.com.zupacademy.caico.proposta.clientesfeign;

import br.com.zupacademy.caico.proposta.avisoviagens.AvisoViagensRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zupacademy.caico.proposta.associacartao.RetornoAPICartao;
import br.com.zupacademy.caico.proposta.associacartao.ResultadoConsultasCartao;
import br.com.zupacademy.caico.proposta.bloqueiocartao.SistemaLegadoCartaoRequest;
import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.AnalisePropostaRequest;
import br.com.zupacademy.caico.proposta.health.RetornoHealthAPisExternas;

@FeignClient(name = "contas", url = "${contas-feign.url}")
public interface VerificaContaFeign {
	
	@RequestMapping(method = RequestMethod.GET, value = "/actuator/health/")
	RetornoHealthAPisExternas getStatusAPI();
	

	@RequestMapping(method = RequestMethod.POST, value = "api/cartoes")
	RetornoAPICartao getNumeroCartao(@RequestBody AnalisePropostaRequest proposta);
	
	@RequestMapping(method = RequestMethod.POST, value = "api/cartoes/{id}/bloqueios")
	ResultadoConsultasCartao bloquearCartao(@RequestBody SistemaLegadoCartaoRequest sistemaResponsavel, @PathVariable String id);

	@RequestMapping(method = RequestMethod.POST, value = "api/cartoes/{id}/avisos")
	ResultadoConsultasCartao avisoViagem(@RequestBody AvisoViagensRequest AvisoViagem, @PathVariable String id);
}
