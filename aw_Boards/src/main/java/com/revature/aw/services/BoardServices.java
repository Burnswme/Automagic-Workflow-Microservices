package com.revature.aw.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.json.Json;
import javax.json.JsonValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.aw.dao.Dao;
import com.revature.aw.domain.Board;

@Component("BoardServices")
@Transactional
public class BoardServices
{
	@Autowired
	private Dao dao;
	
	public List<Board> getBoardsByBoardIds(int[] boardIds)
	{
		List<Board> boards = new ArrayList<>();
		for(int i = 0; i < boardIds.length; i++) {
			boards.add(dao.findOne(boardIds[i]));
		}
		return boards;
	}
	
	/**
	 * Service method that calls the Crud Repository to retrieve all boards.
	 * @return A List of all boards in the database.
	 */
	public List<Board> getAllBoards() {
		return dao.findAll();
	}
	
	/**
	 * Service method that calls the Crud Repository to retrieve a specific board by its id.
	 * @param id The id of the board you want to retrieve.
	 * @return A Board with the given id, or null if it doesn't exist.
	 */
	public Board getBoardByBoardId(int id)
	{
		Board board = new Board();
		board = dao.findOne(id);
		return board;
	}
	
	/**
	 * Service method that calls the Crud Repository to create/update a board.
	 * @param board A board that is to be created/updated.
	 * @return The Board that was created/updated.
	 */
	public Board updateBoard(Board board)
	{
		Board updatedBoard = new Board();
		updatedBoard = dao.save(board);
		if(updatedBoard != null)
		{
			return updatedBoard;
		}
		else
			return null;
	}
	
	/**
	 * Service method that calls the Crud Repository to delete a board.
	 * @param board A board that is to be deleted, should have a valid/existing id.
	 */
	public void deleteBoard(Board board)
	{
		dao.delete(board);
	}
	
//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}
	
	/**
	 * A RestTemplate test for accessing a different microservice. Mostly for a proof of concept. Very roundabout way of accessing history when you can just directly
	 * access the history microservice.
	 * @param boardId
	 * @param accessToken
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "defaultHistory")
	public ResponseEntity<List> getHistory(int boardId, String accessToken) {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create("http://localhost:8004/getHistoryByBoardId/" + boardId + "?access_token=" + accessToken);
		ResponseEntity<List> test3 = restTemplate.getForEntity(uri, List.class);
		
		return test3;
	}
	
	/**
	 * Sample CircuitBreaker default history that returns a default 'history' if the history microservice is down.
	 * @return
	 */
	public ResponseEntity<List> defaultHistory(int boardId, String accessToken) {
		List defaultList = new ArrayList();
		LinkedHashMap<String, Object> defaultHistory = new LinkedHashMap<>();
		defaultHistory.put("id", (Integer)0);
		defaultHistory.put("userId", (Integer)0);
		defaultHistory.put("boardId", boardId);
		defaultHistory.put("action","There is no history service available at the moment. No History will be recorded.");
		defaultHistory.put("timestamp", null);
		System.out.println(defaultHistory);
		
		defaultList.add(defaultHistory);
		ResponseEntity<List> test = new ResponseEntity<>(defaultList, HttpStatus.OK);
		return test;
	}
}
