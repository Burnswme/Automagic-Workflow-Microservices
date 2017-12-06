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

import com.revature.aw.domain.Story;
import com.revature.aw.message.StorySource;
import com.revature.aw.services.StoryServices;

@EnableEurekaClient
@EnableResourceServer
@EnableBinding(Sink.class)
@SpringBootApplication
public class AwStoryApplication {
	@Autowired
	private StoryServices service;
	@Autowired
	private StorySource source;

	public static void main(String[] args) {
		SpringApplication.run(AwStoryApplication.class, args);
	}
	
	@StreamListener(target=Sink.INPUT)
	public void handleDelete(int swimlaneId) {
		for (Story story: service.removeBySwimlaneId(swimlaneId)) {
			source.taskChannel().send(
					MessageBuilder.withPayload(story.getId()).build()
				);
		}
	}
}
