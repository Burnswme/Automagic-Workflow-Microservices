package com.revature.aw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.revature.aw.domain.Swimlane;
import com.revature.aw.message.SwimlaneSource;
import com.revature.aw.service.SwimlaneService;

@EnableEurekaClient
@EnableResourceServer
@EnableBinding(Sink.class)
@SpringBootApplication
public class SwimlaneApplication {
	@Autowired
	private SwimlaneService service;
	@Autowired
	private SwimlaneSource source;

	public static void main(String[] args) {
		SpringApplication.run(SwimlaneApplication.class, args);
	}
	
	@StreamListener(target=Sink.INPUT)
	public void handleDelete(int boardId) {
		System.out.println("id = " + boardId);
		for (Swimlane sl: service.removeByBoardId(boardId)) {
			source.storyChannel().send(
					MessageBuilder.withPayload(sl.getId()).build()
				);
		}
	}
}
