package com.revature.aw.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AwTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwTasksApplication.class, args);
	}
}
