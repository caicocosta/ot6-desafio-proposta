package br.com.zupacademy.caico.proposta.associacarteira;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class CarteirasCartoes {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String carteira;

    @NotNull
    @ManyToOne
    private Cartoes cartao;

    @Deprecated
    public CarteirasCartoes(){

    }

    public CarteirasCartoes(String carteira, Cartoes cartao) {
        this.carteira = carteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
