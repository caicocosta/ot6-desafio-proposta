package br.com.zupacademy.caico.proposta.bloqueiocartao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import br.com.zupacademy.caico.proposta.associacartao.CartoesRepository;
import br.com.zupacademy.caico.proposta.associacartao.HistoricoCartaoRepository;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;

@RestController
public class BloqueioCartaoController {

	@Autowired
	private CartoesRepository cartoesRepository;
	
	@Autowired
	private HistoricoCartaoRepository historicoCartaoRepository;
	
	@PutMapping("/cartoes/bloqueio/{id}")
	public void bloquearCartao(HttpServletRequest request, @PathVariable Long id) {
		Cartoes cartao = cartoesRepository.findById(id).orElseThrow(
				() -> new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado!"));
		
		if (permiteBloqueio(id)) {
			HistoricoCartao novoHistorico = cartao.bloqueiaCartao(request);
			historicoCartaoRepository.save(novoHistorico);	
		}	
		
	}

	private boolean permiteBloqueio(Long id) {
		List<HistoricoCartao> historico = historicoCartaoRepository.findByCartaoId(id);
		for (HistoricoCartao historicoCartao : historico) {
			if(historicoCartao.getStatus() == StatusCartao.BLOQUEADO) {
				throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "O cartão já está bloqueado");
			}
		}
		return true;
	}
}
