package br.com.zupacademy.caico.proposta.associacarteira;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociaCarteiraRequest {

    @NotBlank @Email
    private String email;

    private String carteira;

    @Deprecated
    public AssociaCarteiraRequest(){

    }

    public AssociaCarteiraRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public CarteirasCartoes toModel(Cartoes cartao) {
        return new CarteirasCartoes(
            this.carteira,
            cartao
        );
    }
}
