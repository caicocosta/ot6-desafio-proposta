package br.com.zupacademy.caico.proposta.bloqueiocartao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import br.com.zupacademy.caico.proposta.associacartao.CartoesRepository;
import br.com.zupacademy.caico.proposta.associacartao.HistoricoCartaoRepository;
import br.com.zupacademy.caico.proposta.clientesfeign.VerificaContaFeign;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;
import feign.FeignException.FeignClientException;

@RestController
public class BloqueioCartaoController {
	//1
	@Autowired
	private CartoesRepository cartoesRepository;
	//1
	@Autowired
	private HistoricoCartaoRepository historicoCartaoRepository;
	//1
	@Autowired
	private VerificaContaFeign verificaContaFeign;
	
	@PutMapping("/cartoes/bloqueio/{id}")
	public void bloquearCartao(HttpServletRequest request, @PathVariable Long id) {
		//1
		Cartoes cartao = cartoesRepository.findById(id).orElseThrow(
				() -> new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado!"));
		//1
		if (permiteBloqueio(cartao)) {
			HistoricoCartao novoHistorico = cartao.bloqueiaCartao(request);
			historicoCartaoRepository.save(novoHistorico);	
		}	
		
	}

	private boolean permiteBloqueio(Cartoes cartao) {
		List<HistoricoCartao> historico = historicoCartaoRepository.findByCartaoId(cartao.getId());
		for (HistoricoCartao historicoCartao : historico) {
		//1
			if(historicoCartao.getStatus() == StatusCartao.BLOQUEADO) {
				throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");
			}
		}
		//2
		try {
			SistemaLegadoCartaoRequest requestSistemaLegado = new SistemaLegadoCartaoRequest("MINHA API");
			ResultadoBloqueioCartao status = verificaContaFeign.bloquearCartao(requestSistemaLegado, cartao.getNumCartao());
		} catch (FeignClientException e) {
			if (e.status() == 500) {
				throw new ApiErroException(HttpStatus.BAD_GATEWAY, "Houve um erro ao se conectar ao sevidor.");
			} else if(e.status() == 400) {
				throw new ApiErroException(HttpStatus.BAD_REQUEST, "O cartão já está bloqueado");
			}
			
			return false;
		}
		return true;
	}
}
