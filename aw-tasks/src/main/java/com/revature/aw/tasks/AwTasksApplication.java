package com.revature.aw.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.revature.aw.tasks.service.TaskService;

@EnableEurekaClient
@EnableResourceServer
@EnableBinding(Sink.class)
@SpringBootApplication
public class AwTasksApplication {
	@Autowired
	private TaskService service;

	public static void main(String[] args) {
		SpringApplication.run(AwTasksApplication.class, args);
	}
	
	@StreamListener(target=Sink.INPUT)
	public void handleDelete(int storyId) {
		System.out.println("id = " + storyId);
		service.deleteByStoryId(storyId);
	}
}
