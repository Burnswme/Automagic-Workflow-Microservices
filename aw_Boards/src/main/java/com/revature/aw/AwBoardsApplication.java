package com.revature.aw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.revature.aw.services.BoardServices;

@CrossOrigin(allowedHeaders="*",allowCredentials="true")
//@EnableBinding(Sink.class)
@EnableEurekaClient
@SpringBootApplication
public class AwBoardsApplication {
	@Autowired
	private BoardServices services;

	public static void main(String[] args) {
		SpringApplication.run(AwBoardsApplication.class, args);
	}
	
//	@StreamListener(target=Sink.INPUT, condition="headers['method'].equals('get')")
//	public void log(int id) {
//		System.out.println(id);
//		System.out.println(services.getBoardByBoardId(id));
//	}
}
