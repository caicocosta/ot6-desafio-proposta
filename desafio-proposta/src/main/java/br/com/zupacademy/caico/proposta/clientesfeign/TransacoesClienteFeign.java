package br.com.zupacademy.caico.proposta.clientesfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zupacademy.caico.proposta.health.RetornoHealthAPisExternas;

@FeignClient(name = "transacoes", url = "${transacoes-feign.url}")
public interface TransacoesClienteFeign {
	
	@RequestMapping(method = RequestMethod.GET, value = "/actuator/health/")
	RetornoHealthAPisExternas getStatusAPI();

}
