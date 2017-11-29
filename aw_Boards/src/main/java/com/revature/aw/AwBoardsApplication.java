package com.revature.aw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

=======
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.revature.aw.domain.Board;
import com.revature.aw.services.BoardServices;

@EnableEurekaClient
@EnableBinding(Sink.class)
>>>>>>> 6ae1750e87341d0833099df943e4a8f3b9c0fd67
@SpringBootApplication
public class AwBoardsApplication {
	@Autowired
	private BoardServices services;

	public static void main(String[] args) {
		SpringApplication.run(AwBoardsApplication.class, args);
	}
	
	@StreamListener(target=Sink.INPUT, condition="headers['method'].equals('get')")
	public void log(int id) {
		System.out.println(id);
		System.out.println(services.getBoardByBoardId(id));
	}
}
