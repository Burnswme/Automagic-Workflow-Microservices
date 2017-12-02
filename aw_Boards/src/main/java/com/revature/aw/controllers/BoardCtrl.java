package com.revature.aw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.Board;
import com.revature.aw.services.BoardServices;

@RestController
public class BoardCtrl {
	@Autowired
	private BoardServices services;
	
	@GetMapping("/getOwnerBoards")
	@ResponseBody
	public ResponseEntity<Object> getBoards(@PathVariable("users") int[] id) {
		List<Board> userBoards = new ArrayList<>();
		userBoards = services.getBoardsByUserId(id);
		if(userBoards != null) {
			return new ResponseEntity<>(userBoards, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/getBoard/{id}")
	@ResponseBody
	public ResponseEntity<Board> getBoardById(@PathVariable("id") int id) {
		Board board = services.getBoardByBoardId(id);
		
		return (board != null) ? new ResponseEntity<>(board, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PostMapping("/deleteBoard")
	public ResponseEntity<Object> deleteBoard(@RequestBody Board board, HttpServletRequest req) {
		if(services != null && board != null && services.getBoardByBoardId(board.getId()) != null) {
			services.deleteBoard(board);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else if (services.getBoardByBoardId(board.getId()) == null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/createBoard")
	@ResponseBody
	public ResponseEntity<Object> createBoard(@RequestBody Board board, HttpServletRequest req) {
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
	
}
