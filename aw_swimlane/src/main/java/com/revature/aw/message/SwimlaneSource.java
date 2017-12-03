package com.revature.aw.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SwimlaneSource {

	@Output("story-rabbit")
	MessageChannel storyChannel();

}
