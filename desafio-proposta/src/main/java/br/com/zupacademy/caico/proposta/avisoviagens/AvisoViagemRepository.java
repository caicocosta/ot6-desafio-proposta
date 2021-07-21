package br.com.zupacademy.caico.proposta.avisoviagens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends JpaRepository <AvisoViagem, Long> {

}
