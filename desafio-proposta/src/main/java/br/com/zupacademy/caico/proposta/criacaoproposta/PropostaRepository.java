package br.com.zupacademy.caico.proposta.criacaoproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Propostas, Long>{

	Propostas findByDocumento(String documento);

}
