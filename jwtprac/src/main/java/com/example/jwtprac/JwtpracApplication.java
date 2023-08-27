package com.example.jwtprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class JwtpracApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtpracApplication.class, args);
	}

}
