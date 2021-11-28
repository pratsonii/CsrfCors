package com.example.demo;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().configurationSource(corsConfigurationSource()).and().
//				csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().
		csrf().and().
		authorizeRequests().antMatchers("/app/**").authenticated().and().httpBasic();
		
		http.addFilterAfter(new CookieFilter(), CsrfFilter.class);
		
		http.addFilterAfter(new AfterFilterSecurity(), FilterSecurityInterceptor.class);
	}

	private CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Collections.singletonList("*"));
		config.setExposedHeaders(List.of("CSRF-TOKEN-VALUE")); // This will allow JS to get the value of header. If we dont set then it will be displayed but cant be fetched by JS.
		config.setMaxAge(3600L);

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("a").password("{noop}a").roles("USER"); // if we dont append {noop} it will ask for password decryptor}
	}
}
