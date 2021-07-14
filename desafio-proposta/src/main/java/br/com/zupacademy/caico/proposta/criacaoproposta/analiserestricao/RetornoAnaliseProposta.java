package br.com.zupacademy.caico.proposta.criacaoproposta.analiserestricao;

public class RetornoAnaliseProposta {

	private String documento;
	private String nome;
	private StatusRestricao resultadoSolicitacao;
	private String proposta;
	
	public StatusRestricao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProposta() {
		return proposta;
	}

	public void setProposta(String proposta) {
		this.proposta = proposta;
	}

	public void setResultadoSolicitacao(StatusRestricao resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}
	
}
