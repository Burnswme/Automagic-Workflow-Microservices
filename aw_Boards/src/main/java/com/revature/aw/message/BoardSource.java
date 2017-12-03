package com.revature.aw.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BoardSource {

	@Output("bur-rabbit")
	MessageChannel boardUserRoleChannel();
	
	
	@Output("swimlane-rabbit")
	MessageChannel swimlaneChannel();
	
}
