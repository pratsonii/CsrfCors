package com.example.demo;

import java.util.Map;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

	@GetMapping("/login")
	public Map<String, String> hello() {
		System.out.println(" login request" );
		return Map.of("staus", "Login success");
	}
	
	@GetMapping("/cookie")
	public Map<String, String> cookie(@CookieValue("JSESSIONID") String jsessionId
//			, @CookieValue("XSRF-TOKEN") String csrf 
			) {
		System.out.println("jsession : " + jsessionId);
//		System.out.println("XSRF-TOKEN : " + csrf);
		return Map.of("staus", "Login success");
	}
	
	@PostMapping("/csrf")
	public Map<String, String> postCsrf(@CookieValue("JSESSIONID") String jsessionId) {
		System.out.println("jsession : " + jsessionId);
		return Map.of("staus", "Login success");
	}
}
