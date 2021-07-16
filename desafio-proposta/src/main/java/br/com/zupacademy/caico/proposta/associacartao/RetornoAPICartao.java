package br.com.zupacademy.caico.proposta.associacartao;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.caico.proposta.criacaoproposta.Propostas;

public class RetornoAPICartao {

	@NotBlank
	private String id;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotBlank
	private String titular;
	
	private Set<String> bloqueios;
	private Set<String> avisos;
	private Set<String> carteiras;
	private Set<Integer> parcelas;

	@NotNull
	private Integer limite;
	private Boolean renegociacao;
	
	@NotNull
	private Long idProposta;
	
	@Deprecated
	public RetornoAPICartao() {
		
	}

	public RetornoAPICartao(@NotBlank String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular,
			Set<String> bloqueios, Set<String> avisos, Set<String> carteiras, Set<Integer> parcelas,
			@NotNull Integer limite, Boolean renegociacao, @NotNull Long idProposta) {
		super();
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.bloqueios = bloqueios;
		this.avisos = avisos;
		this.carteiras = carteiras;
		this.parcelas = parcelas;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.idProposta = idProposta;
	}



	public String getId() {
		return id;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Set<String> getBloqueios() {
		return bloqueios;
	}

	public Set<String> getAvisos() {
		return avisos;
	}

	public Set<String> getCarteiras() {
		return carteiras;
	}

	public Set<Integer> getParcelas() {
		return parcelas;
	}

	public Integer getLimite() {
		return limite;
	}

	public Boolean getRenegociacao() {
		return renegociacao;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public Cartoes toModel(Propostas proposta) {

		Cartoes cartao = new Cartoes(
				id, 
				emitidoEm, 
				titular, 
				limite, 
				renegociacao, 
				proposta);
		
		return cartao;
	}
	
}
