package br.com.zupacademy.caico.proposta.associacartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.caico.proposta.clientesfeign.VerificaContaFeign;
import br.com.zupacademy.caico.proposta.criacaoproposta.PropostaRepository;
import br.com.zupacademy.caico.proposta.criacaoproposta.Propostas;
import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.AnalisePropostaRequest;
import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.StatusProposta;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;
import feign.FeignException.FeignClientException;
import feign.FeignException.FeignServerException;
import feign.RetryableException;

@Component
public class AssociaCartoesSchedule {

	@Autowired
	private VerificaContaFeign verificaContaFeign;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private CartoesRepository cartoesRepository;
	
	@Async
	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
	public void associarCartao() {
		
		List<Propostas> proposta = propostaRepository.findByStatus(StatusProposta.ELEGIVEL);
		
		if(proposta.size() >= 1) {
			adicionaCartao(proposta);	
		}
	}

	private void adicionaCartao(List<Propostas> proposta) {
		for (Propostas propostas : proposta) {
			AnalisePropostaRequest request = new AnalisePropostaRequest(
					propostas.getDocumento(), 
					propostas.getNome(),
					String.valueOf(propostas.getId()));
			
			
			try {
				RetornoAPICartao retornoAPICartao = verificaContaFeign.getNumeroCartao(request);
				Cartoes cartao = retornoAPICartao.toModel(propostas);
				cartoesRepository.save(cartao);
				propostas.setStatus(StatusProposta.CONCLUIDA);
				propostaRepository.save(propostas);
			} catch (RetryableException e) {
				throw new ApiErroException(HttpStatus.BAD_GATEWAY, e.getMessage());
			}
		}
	}
}
