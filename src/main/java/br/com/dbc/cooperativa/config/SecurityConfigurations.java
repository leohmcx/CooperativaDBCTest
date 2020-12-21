package br.com.dbc.cooperativa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Override // Autorização -> URL, perfil de acesso a URL, etc..
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/**").permitAll()
		.antMatchers(HttpMethod.POST, "/**").permitAll()
		.antMatchers(HttpMethod.PUT, "/**").permitAll()
		.anyRequest().authenticated()
		.and().headers().frameOptions().disable()
		.and().headers().frameOptions().sameOrigin()
		.and().csrf().disable() // evita ataque Cross-site request forgery
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // informa que será Stateless		
	}	
}