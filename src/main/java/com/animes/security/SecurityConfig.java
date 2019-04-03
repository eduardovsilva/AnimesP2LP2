package com.animes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsImplementation repository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/h2-console/**").hasRole("ADMIN")
		.mvcMatchers(HttpMethod.POST, "/animes", "/generos").hasRole("ADMIN")
		.mvcMatchers(HttpMethod.PUT, "/animes/{id}", "/generos/{id}").hasRole("ADMIN")
		.mvcMatchers(HttpMethod.DELETE, "/animes/{id}", "/generos/{id}").hasRole("ADMIN")
		.mvcMatchers(HttpMethod.GET, "/generos", "/usuarios",  "/avaliacoes", "/lista").hasRole("ADMIN")
		.mvcMatchers(HttpMethod.GET,"/animes", "/animes/{id}", "/usuarios/{login}").permitAll()
		.mvcMatchers(HttpMethod.POST, "/usuarios").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and().headers().frameOptions().disable()
		.and().httpBasic();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(repository)
		.passwordEncoder(new BCryptPasswordEncoder());
	
	}
	
}
