package com.revature.aw.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(allowedHeaders="*",allowCredentials="true")
@SpringBootApplication
public class AwAuthApplication {
//	private static final Log logger = LogFactory.getLog(AwAuthApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AwAuthApplication.class, args);
	}
}
