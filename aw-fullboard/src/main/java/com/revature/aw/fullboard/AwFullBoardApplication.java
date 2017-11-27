package com.revature.aw.fullboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AwFullBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwFullBoardApplication.class, args);
	}
}
