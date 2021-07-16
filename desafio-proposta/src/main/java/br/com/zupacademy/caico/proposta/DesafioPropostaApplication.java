package br.com.zupacademy.caico.proposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableAsync
@EnableScheduling
public class DesafioPropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPropostaApplication.class, args);
	}

}
