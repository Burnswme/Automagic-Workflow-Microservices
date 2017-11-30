package com.revature.aw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableEurekaClient
@EnableResourceServer
@SpringBootApplication
public class AwBoardValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwBoardValidationApplication.class, args);
	}
}
