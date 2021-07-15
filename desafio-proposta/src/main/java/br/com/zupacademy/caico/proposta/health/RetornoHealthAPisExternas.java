package br.com.zupacademy.caico.proposta.health;

import org.springframework.boot.actuate.health.Status;

public class RetornoHealthAPisExternas {

	private Status status;

	public Status getStatus() {
		return status;
	}
	
}
