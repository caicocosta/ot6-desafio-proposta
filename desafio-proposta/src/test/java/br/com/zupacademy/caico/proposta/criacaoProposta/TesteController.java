package br.com.zupacademy.caico.proposta.criacaoProposta;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TesteController {

	@Autowired
	private MockMvc mockMvc;
	
	/*@Test
	public void deveriaDevolver400CasoDadosEstejamIncorretos() throws Exception {
		
		URI uri = new URI("/propostas");
		String json = "{\"documento\": \"09793051605\" \"email\": \"caico@email.com\", \"nome\": \"caico\"}";
	
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}
	
	/*@Test
	public void deveriaDevolver201CasoDadosEstejamCorretos() throws Exception {
		
		URI uri = new URI("/propostas");
		String json = 
			"{\"documento\": \"09793051604\", \"email\": \"caico@email.com\", " +
		    "\"nome\": \"caico\", \"endereco\": \"teste\", \"salario\": \"5000\"}";
	
		mockMvc.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201));
	}*/
}
