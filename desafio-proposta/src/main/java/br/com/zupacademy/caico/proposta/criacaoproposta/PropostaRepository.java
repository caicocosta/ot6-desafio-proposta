package br.com.zupacademy.caico.proposta.criacaoproposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao.StatusProposta;

@Repository
public interface PropostaRepository extends JpaRepository<Propostas, Long>{

	Propostas findByDocumento(String documento);

	List<Propostas> findByStatus(StatusProposta elegivel);

}
