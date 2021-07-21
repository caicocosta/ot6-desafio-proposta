package br.com.zupacademy.caico.proposta.associacartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoesRepository extends JpaRepository<Cartoes, Long>{

    Cartoes findByNumCartao(String NumCartao);
}
