package br.com.zupacademy.caico.proposta.avisoviagens;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import br.com.zupacademy.caico.proposta.associacartao.CartoesRepository;
import br.com.zupacademy.caico.proposta.associacartao.ResultadoConsultasCartao;
import br.com.zupacademy.caico.proposta.clientesfeign.VerificaContaFeign;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AvisoViagensController {

    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private VerificaContaFeign verificaContaFeign;

    @PostMapping("/cartoes/{numcartao}/avisoviagem")
    public void cadastraAviso(
            HttpServletRequest request,
            @RequestBody @Valid AvisoViagensRequest avisoViagensRequest,
            @PathVariable String numcartao){

        Cartoes cartao = cartoesRepository.findByNumCartao(numcartao);

        if (pertmiteCriarAviso(cartao, avisoViagensRequest)){
            AvisoViagem avisoViagem = avisoViagensRequest.toModel(cartao, request);
            avisoViagemRepository.save(avisoViagem);
        }

    }

    private boolean pertmiteCriarAviso(Cartoes cartao, AvisoViagensRequest avisoViagensRequest){
        if (cartao == null){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não cadastrado");
        }

        try {
            ResultadoConsultasCartao resultado = verificaContaFeign.avisoViagem(avisoViagensRequest, cartao.getNumCartao());
        }catch (FeignException.FeignClientException e){
            if (e.status() == 400){
                throw new ApiErroException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

            throw new ApiErroException(HttpStatus.BAD_GATEWAY, "Houve um erro no servidor ao processar essa requisição.");
        }
        return true;
    }

}

