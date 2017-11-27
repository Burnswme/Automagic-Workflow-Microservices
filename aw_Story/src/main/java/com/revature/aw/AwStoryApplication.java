package com.revature.aw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AwStoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwStoryApplication.class, args);
	}
}
