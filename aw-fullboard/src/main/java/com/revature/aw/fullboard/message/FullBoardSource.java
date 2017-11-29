package com.revature.aw.fullboard.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FullBoardSource {
	@Output("boards")
	MessageChannel boards();

}
