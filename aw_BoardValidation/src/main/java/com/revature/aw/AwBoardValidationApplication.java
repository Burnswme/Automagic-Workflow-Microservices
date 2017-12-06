package com.revature.aw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.revature.aw.services.BoardUserRoleServices;

@EnableEurekaClient
@EnableResourceServer
@EnableBinding(Sink.class)
@SpringBootApplication
public class AwBoardValidationApplication {
	@Autowired
	private BoardUserRoleServices service;

	public static void main(String[] args) {
		SpringApplication.run(AwBoardValidationApplication.class, args);
	}
	
	@StreamListener(target=Sink.INPUT)
	public void handleDelete(int boardId) {
		service.deleteByBoardId(boardId);
	}
}
