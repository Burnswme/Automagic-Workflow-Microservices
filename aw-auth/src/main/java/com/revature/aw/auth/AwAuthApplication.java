package com.revature.aw.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
public class AwAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwAuthApplication.class, args);
	}
}
