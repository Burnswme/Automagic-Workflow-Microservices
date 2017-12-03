package com.revature.aw.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface StorySource {

	@Output("task-rabbit")
	MessageChannel taskChannel();

}
