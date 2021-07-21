package br.com.zupacademy.caico.proposta.avisoviagens;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String destino;

    @NotNull @Future
    private LocalDate dataTerminoViagem;

    @NotBlank
    private String ipOrigem;

    @NotBlank
    private String userAgent;

    @NotNull
    @ManyToOne
    private Cartoes cartao;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    public AvisoViagem(String destino, LocalDate dataTerminoViagem, String ipOrigem, String userAgent, Cartoes cartao) {
        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
        this.ipOrigem = ipOrigem;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

}
