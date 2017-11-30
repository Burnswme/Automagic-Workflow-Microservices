package com.revature.aw.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AwConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwConfigServerApplication.class, args);
	}
}
