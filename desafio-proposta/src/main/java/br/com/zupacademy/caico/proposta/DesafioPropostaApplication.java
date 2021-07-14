package br.com.zupacademy.caico.proposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DesafioPropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPropostaApplication.class, args);
	}

}
