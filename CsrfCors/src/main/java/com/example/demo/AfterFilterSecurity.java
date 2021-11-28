package com.example.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

public class AfterFilterSecurity extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
		
		System.out.println(" AfterFilterSecurity before filter : " + csrfToken.getToken());
		
		response.setHeader("CSRF-TOKEN-VALUE", csrfToken.getToken());
		
		filterChain.doFilter(request, response);
		
		csrfToken = (CsrfToken) request.getAttribute("_csrf");

		System.out.println(" AfterFilterSecurity after filter : " + csrfToken.getToken());
		
		response.setHeader("CSRF-TOKEN-VALUE", csrfToken.getToken());

	}

}
