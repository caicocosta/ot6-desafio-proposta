package br.com.zupacademy.caico.proposta.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configuracoes de autenticacao... A parte de perfil de acessos, quem pode acessar cada recurso.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/usuarios").permitAll()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.antMatchers(HttpMethod.POST, "/gera-nota").permitAll()
			.antMatchers(HttpMethod.POST, "/processa-ranking").permitAll()
			.antMatchers(HttpMethod.GET, "/actuator").permitAll()
			.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
			.antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_escopo-cartoes")
			.anyRequest().authenticated()
			.and().cors()
			.and().csrf().disable()
			.oauth2ResourceServer()
			.jwt();
	}
	
	//Configuracoes de recursos de acessos estáticos (js, css, imagens e etc).
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/**", "/webjars/**", "/configuration/", "swagger-resources/**");
	}

}
