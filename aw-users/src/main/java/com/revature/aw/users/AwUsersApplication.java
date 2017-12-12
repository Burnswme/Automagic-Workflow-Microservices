package com.revature.aw.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class AwUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwUsersApplication.class, args);
	}
}
