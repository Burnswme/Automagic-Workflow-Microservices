package com.revature.aw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//@EnableBinding(Sink.class)
@EnableEurekaClient
@EnableResourceServer
@SpringBootApplication
public class AwBoardsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AwBoardsApplication.class, args);
	}
	
//	@StreamListener(target=Sink.INPUT, condition="headers['method'].equals('get')")
//	public void log(int id) {
//		System.out.println(id);
//		System.out.println(services.getBoardByBoardId(id));
//	}
}
