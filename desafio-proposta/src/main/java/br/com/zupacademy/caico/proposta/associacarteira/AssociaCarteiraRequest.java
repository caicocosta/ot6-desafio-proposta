package br.com.zupacademy.caico.proposta.associacarteira;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociaCarteiraRequest {

    @NotBlank @Email
    private String email;

    @NotNull
    private Carteira carteira;

    @Deprecated
    public AssociaCarteiraRequest(){

    }

    public AssociaCarteiraRequest(String email, Carteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    public CarteirasCartoes toModel(Cartoes cartao) {
        return new CarteirasCartoes(
            this.carteira,
            cartao
        );
    }
}
