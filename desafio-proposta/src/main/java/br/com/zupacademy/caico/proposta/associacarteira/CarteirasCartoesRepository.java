package br.com.zupacademy.caico.proposta.associacarteira;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteirasCartoesRepository extends JpaRepository<CarteirasCartoes, Long> {
    CarteirasCartoes findByCartaoIdAndCarteira(Long cartaoId, Carteira carteira);
}
