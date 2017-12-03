package com.revature.aw.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.Board;
import com.revature.aw.domain.History;
import com.revature.aw.message.BoardSource;
import com.revature.aw.services.BoardServices;

@RestController
public class BoardCtrl {
	@Autowired
	private BoardServices services;
	
	@Autowired
	BoardSource source;
	
	@PostMapping("/getBoards")
	@ResponseBody
	public ResponseEntity<List<Board>> getBoards(@RequestBody int[] ids) {
		List<Board> userBoards = services.getBoardsByBoardIds(ids);
		System.out.println(userBoards);
		return new ResponseEntity<>(userBoards, HttpStatus.OK);
	}
	
	@GetMapping("/getAllBoards")
	@ResponseBody
	public ResponseEntity<List<Board>> getAllBoards() {
		List<Board> boards = services.getAllBoards();
		return (boards != null) ? new ResponseEntity<>(boards, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/getBoard/{id}")
	@ResponseBody
	public ResponseEntity<Board> getBoardById(@PathVariable("id") int id) {
		Board board = services.getBoardByBoardId(id);
		return (board != null) ? new ResponseEntity<>(board, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
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
	
	@GetMapping("/getHistory/{boardId}")
	public ResponseEntity<Object> getHistory(@PathVariable("boardId") int boardId, HttpServletRequest req) {
		if(services != null && services.getBoardByBoardId(boardId) != null) {
			List<History> history = services.getHistory(boardId);
			for(History hist : history) {
				System.out.println(hist);
			}
			return new ResponseEntity<>(history, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
}
