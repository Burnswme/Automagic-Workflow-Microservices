package com.revature.aw.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.aw.domain.Board;
import com.revature.aw.message.BoardSource;
import com.revature.aw.services.BoardServices;

@RestController
@EnableCircuitBreaker
public class BoardCtrl {
	@Autowired
	private BoardServices services;
	
	@Autowired
	BoardSource source;
	
	@PostMapping("/getBoards")
	@ResponseBody
	public ResponseEntity<List<Board>> getBoards(@RequestBody int[] ids) {
		List<Board> userBoards = services.getBoardsByBoardIds(ids);
		return new ResponseEntity<>(userBoards, HttpStatus.OK);
	}
	
	/**
	 * Board REST Endpoint used to retrieve all boards.
	 * @return A ResponseEntity object with a list of all boards attached to it. Status OK if good, Status CONFLICT if bad.
	 */
	@GetMapping("/getAllBoards")
	@ResponseBody
	public ResponseEntity<List<Board>> getAllBoards() {
		List<Board> boards = services.getAllBoards();
		return (boards != null) ? new ResponseEntity<>(boards, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/**
	 * Board REST Endpoint used to retrieve a specific board.
	 * @param id Id of the board you wish to retrieve. Should be in the url path.
	 * @return A ResponseEntity object with the appropriate board attached to it. Status OK if good, Status CONFLICT if bad.
	 */
	@GetMapping("/getBoard/{id}")
	@ResponseBody
	public ResponseEntity<Board> getBoardById(@PathVariable("id") int id) {
		Board board = services.getBoardByBoardId(id);
		return (board != null) ? new ResponseEntity<>(board, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/**
	 * Board REST Endpoint for deleting a board.
	 * @param board A Board Object to be passed in the Request Body. Should have a valid id(i.e. it exists in the database).
	 * @param req The HttpRequest.
	 * @return A ResponseEntity object with true attached if it succeeded. Status OK if good, Status CONFLICT if bad.
	 */
	@PostMapping("/deleteBoard")
	public ResponseEntity<Boolean> deleteBoard(@RequestBody Board board, HttpServletRequest req) {
		if(services != null && board != null && services.getBoardByBoardId(board.getId()) != null) {
			services.deleteBoard(board);
			source.boardUserRoleChannel().send(
					MessageBuilder.withPayload(board.getId()).build()
				);
			source.swimlaneChannel().send(
					MessageBuilder.withPayload(board.getId()).build()
				);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else if (services.getBoardByBoardId(board.getId()) == null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	/**
	 * Board REST Endpoint to create a Board passed from the Request Body.
	 * @param board A Board Object to be created, passed in the Request Body.
	 * @return A ResponseEntity object with the created Board attached. Status OK if good, Status CONFLICT if bad.
	 */
	@PostMapping("/createBoard")
	@ResponseBody
	public ResponseEntity<Board> createBoard(@RequestBody Board board) {
		if (services != null) {
			board = services.updateBoard(board);
			return new ResponseEntity<>(board, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Board REST Endpoint to update a Board, apssed from the Request Body.
	 * @param board A Board Object to be updated, passed in the Request Body. Should have a valid id(i.e. it exists in the database).
	 * @param req The HttpRequest.
	 * @return A ResponseEntity object with the updated Board attached. Status OK if good, Status CONFLICT if bad.
	 */
	@PostMapping("/updateBoard")
	@ResponseBody
	public ResponseEntity<Object> updateBoard(@RequestBody Board board, HttpServletRequest req) {
		if (services != null && board != null && services.getBoardByBoardId(board.getId()) != null) {
			board = services.updateBoard(board);
			return new ResponseEntity<>(board, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	/**
	 * Sample Endpoint to 'add' RestTemplate and Circuit Breaking to the project. Less efficient and doesn't make anything really better, but more a proof of concept than
	 * anything.
	 * @param boardId
	 * @param req
	 * @return
	 */
	@GetMapping("/getHistory/{boardId}")
	public ResponseEntity<Object> getHistory(@PathVariable("boardId")int boardId, HttpServletRequest req) {
		return new ResponseEntity<>(services.getHistory(boardId, req.getParameter("access_token")), HttpStatus.OK);
	}
	
}
