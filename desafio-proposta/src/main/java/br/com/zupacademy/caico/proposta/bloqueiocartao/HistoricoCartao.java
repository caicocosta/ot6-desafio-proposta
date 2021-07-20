package br.com.zupacademy.caico.proposta.bloqueiocartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.caico.proposta.associacartao.Cartoes;

@Entity
public class HistoricoCartao {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private StatusCartao status = StatusCartao.ENVIADO;
	
	@NotBlank
	private String ipOrigemRequisicao;
	
	@NotBlank
	private String userAgent;
	
	@ManyToOne
	private Cartoes cartao;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	
	@Deprecated
	private HistoricoCartao() {
		
	}

	public HistoricoCartao(@NotBlank String ipOrigemRequisicao, @NotBlank String userAgent, Cartoes cartao) {
		super();
		this.ipOrigemRequisicao = ipOrigemRequisicao;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}



	public StatusCartao getStatus() {
		return status;
	}

	public void setStatus(StatusCartao status) {
		this.status = status;
	}
	
}
