package com.revature.aw.fullboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.revature.aw.fullboard.message.FullBoardWebEvent;

@EnableEurekaClient
@SpringBootApplication
public class AwFullBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwFullBoardApplication.class, args);
	}
}
