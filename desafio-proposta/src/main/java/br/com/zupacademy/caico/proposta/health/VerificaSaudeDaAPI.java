package br.com.zupacademy.caico.proposta.health;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.zupacademy.caico.proposta.clientesfeign.SolicitacaoClientFeign;
import br.com.zupacademy.caico.proposta.clientesfeign.TransacoesClienteFeign;
import br.com.zupacademy.caico.proposta.clientesfeign.VerificaContaFeign;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;

@Component
public class VerificaSaudeDaAPI implements HealthIndicator{

	@Autowired
	private SolicitacaoClientFeign solicitacaoCliente;
	
	@Autowired
	private VerificaContaFeign verificaConta;

	@Autowired
	private TransacoesClienteFeign transacoesCliente;
	
	@Override
	public Health health() {
		RetornoHealthAPisExternas healthTransacao = transacoesCliente.getStatusAPI();
		RetornoHealthAPisExternas healthContas = verificaConta.getStatusAPI();
		RetornoHealthAPisExternas healthSolitante = solicitacaoCliente.getStatusAPI();
		
		Map<String, Status> detalhes = new HashMap<>();
        detalhes.put("analise_solicitante", healthSolitante.getStatus());
        detalhes.put("contas", healthContas.getStatus());
        detalhes.put("transacoes", healthTransacao.getStatus());
        
        Set<Status> listStatus = new HashSet<>(detalhes.values());

        if (listStatus.size() == 1 && listStatus.contains(Status.UP)) {
            return Health.status(Status.UP).withDetails(detalhes).build();
        }
        throw new ApiErroException(HttpStatus.INTERNAL_SERVER_ERROR, "Houve uma falha interna nas aplicações");
        
	}

}
