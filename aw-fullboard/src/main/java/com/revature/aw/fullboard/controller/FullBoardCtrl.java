package com.revature.aw.fullboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.aw.fullboard.domain.Board;
import com.revature.aw.fullboard.message.FullBoardSource;
import com.revature.aw.fullboard.service.DataService;

@RestController
public class FullBoardCtrl {
	@Autowired
	private DataService ds;
	@Autowired
	private FullBoardSource fbs;
	
	@GetMapping("/getFullBoardById/{id}")
	@ResponseBody
	public ResponseEntity<Board> getFullBoard(@PathVariable("id") int id)
	{
		Board board = ds.getBoardById(id);
		return (board != null) ? new ResponseEntity<>(board, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/testRabbit")
	public void testRabbit()
	{
		fbs.boards().send(
				MessageBuilder
				.withPayload("Message from fullboard to boards")
				.build());
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate buildRestTemplate(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.build();
	}
	

	
	@GetMapping("/testGet/{id}")
	@ResponseBody
	public void getBoardById(@PathVariable("id") int id)
	{
		fbs.boards().send(
				MessageBuilder
				.withPayload(id)
				.setHeader("method", "get")
				.build());
	}

}
