package br.com.zupacademy.caico.proposta.criacaoproposta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class CriptografaTexto {
    @Value("${chave.privada}")
    private String chavePrivada;
    @Value("${chave.publica}")
    private String chavePublica;
    private TextEncryptor textEncryptor;

    public CriptografaTexto() {
        this.textEncryptor = Encryptors.text("e10adc3949ba59abbe56e057f20f883e", "c33367701511b4f6020ec61ded352059");
    }

    public String encriptar(String texto){
        return textEncryptor.encrypt(texto);
    }

    public String decriptar(String textoEncriptado){
        return textEncryptor.decrypt(textoEncriptado);
    }
}
