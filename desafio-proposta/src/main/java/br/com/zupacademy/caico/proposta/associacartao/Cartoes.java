package br.com.zupacademy.caico.proposta.associacartao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.caico.proposta.bloqueiocartao.HistoricoCartao;
import br.com.zupacademy.caico.proposta.bloqueiocartao.StatusCartao;
import br.com.zupacademy.caico.proposta.criacaoproposta.Propostas;

@Entity
public class Cartoes {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String NumCartao;
	
	@NotNull
	private LocalDateTime emitidoEm;
	
	@NotBlank
	private String titular;

	@NotNull
	private Integer limite;
	private Boolean renegociacao;
	
	@NotNull
	@ManyToOne
	private Propostas proposta;
	
	@Deprecated
	public Cartoes() {
		
	}

	public Cartoes(@NotBlank String numCartao, @NotNull LocalDateTime emitidoEm, @NotBlank String titular,
			@NotNull Integer limite, Boolean renegociacao, @NotNull Propostas proposta) {
		super();
		NumCartao = numCartao;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.renegociacao = renegociacao;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getNumCartao() {
		return NumCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public Integer getLimite() {
		return limite;
	}

	public Boolean getRenegociacao() {
		return renegociacao;
	}

	public Propostas getProposta() {
		return proposta;
	}

	public HistoricoCartao bloqueiaCartao(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null) {
		    ipAddress = request.getHeader("X_FORWARDED_FOR");
		    if (ipAddress == null){
		        ipAddress = request.getRemoteAddr();
		    }
		}
		
		String userAgent = request.getHeader("User-Agent");
		
		HistoricoCartao novoHistorico = new HistoricoCartao(ipAddress, userAgent, this);
		novoHistorico.setStatus(StatusCartao.BLOQUEADO);
		return novoHistorico;
	}

}
