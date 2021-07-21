package br.com.zupacademy.caico.proposta.avisoviagens;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagensRequest {

    @NotBlank
    private String destino;

    @NotNull @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTerminoViagem;

    @Deprecated
    public AvisoViagensRequest(){

    }

    public AvisoViagensRequest(String destino, LocalDate dataTerminoViagem) {
        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public void setDataTerminoViagem(LocalDate dataTerminoViagem) {
        this.dataTerminoViagem = dataTerminoViagem;
    }

    public AvisoViagem toModel(Cartoes cartao, HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = request.getHeader("X_FORWARDED_FOR");
            if (ipAddress == null){
                ipAddress = request.getRemoteAddr();
            }
        }

        String userAgent = request.getHeader("User-Agent");

        return new AvisoViagem(
            this.destino,
            this.dataTerminoViagem,
            ipAddress,
            userAgent,
            cartao
        );
    }
}
