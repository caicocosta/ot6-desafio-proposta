package br.com.zupacademy.caico.proposta.avisoviagens;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import br.com.zupacademy.caico.proposta.associacartao.CartoesRepository;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;
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

    @PostMapping("/cartoes/{numcartao}/avisoviagem")
    public void cadastraAviso(
            HttpServletRequest request,
            @RequestBody @Valid AvisoViagensRequest avisoViagensRequest,
            @PathVariable String numcartao){

        System.out.println("Teste: " + numcartao);

        Cartoes cartao = cartoesRepository.findByNumCartao(numcartao);

        if (cartao == null){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não cadastrado");
        }

        AvisoViagem avisoViagem = avisoViagensRequest.toModel(cartao, request);

        avisoViagemRepository.save(avisoViagem);
    }

}

