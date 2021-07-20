package br.com.zupacademy.caico.proposta.associacartao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.caico.proposta.bloqueiocartao.HistoricoCartao;

@Repository
public interface HistoricoCartaoRepository extends JpaRepository<HistoricoCartao, Long>{

	 List<HistoricoCartao> findByCartaoId(Long id);

}
