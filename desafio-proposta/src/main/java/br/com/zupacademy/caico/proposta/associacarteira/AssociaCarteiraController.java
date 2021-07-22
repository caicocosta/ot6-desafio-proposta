package br.com.zupacademy.caico.proposta.associacarteira;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;
import br.com.zupacademy.caico.proposta.associacartao.CartoesRepository;
import br.com.zupacademy.caico.proposta.associacartao.ResultadoConsultasCartao;
import br.com.zupacademy.caico.proposta.clientesfeign.VerificaContaFeign;
import br.com.zupacademy.caico.proposta.exceptionhandler.ApiErroException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class AssociaCarteiraController {

    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private CarteirasCartoesRepository carteirasCartoesRepository;

    @Autowired
    private VerificaContaFeign verificaContaFeign;

    @PostMapping("/cartoes/{numCartao}/associacarteira/paypal")
    public ResponseEntity<?> associaCartao(
            @RequestBody @Valid AssociaCarteiraRequest associaCarteiraRequest,
            @PathVariable String numCartao,
            UriComponentsBuilder uri){

        System.out.println("Teste: " + associaCarteiraRequest.getEmail());
        associaCarteiraRequest.setCarteira("PAYPAL");
        Cartoes cartao = cartoesRepository.findByNumCartao(numCartao);

        if (!PermiteAssociarCarteira(cartao, associaCarteiraRequest)){
           return ResponseEntity.badRequest().build();
        }

        CarteirasCartoes carteira = associaCarteiraRequest.toModel(cartao);

        carteirasCartoesRepository.save(carteira);

        URI uriResponse = uri.path("carteira-associada/{id}").buildAndExpand(carteira.getId()).toUri();
        return ResponseEntity.created(uriResponse).build();
    }

    private boolean PermiteAssociarCarteira(Cartoes cartao, AssociaCarteiraRequest associaCarteiraRequest) {

        if(cartao == null){
            throw new ApiErroException(HttpStatus.NOT_FOUND, "Cartão não encontrado");
        }

        CarteirasCartoes carteira = carteirasCartoesRepository.findByCartaoIdAndCarteira(
                cartao.getId(),
                associaCarteiraRequest.getCarteira());

        if(carteira != null){
            throw new ApiErroException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já associado a essa carteira");
        }

        try {
            ResultadoConsultasCartao resultado = verificaContaFeign.associaCarteira(associaCarteiraRequest, cartao.getNumCartao());
        } catch (FeignException.FeignClientException e){
            if (e.status() == 400){
                throw new ApiErroException(HttpStatus.BAD_REQUEST, "Não foi possível associar o cartão a essa carteira.");
            }

            throw new ApiErroException(HttpStatus.BAD_GATEWAY, "Houve um erro no servidor ao processar essa requisição.");
        }
        return true;
    }

}
