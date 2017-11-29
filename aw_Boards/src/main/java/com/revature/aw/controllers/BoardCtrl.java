package com.revature.aw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.Board;
import com.revature.aw.services.BoardServices;

@RestController
public class BoardCtrl
{
	@Autowired
	private BoardServices services;
	
	@GetMapping("/getOwnerBoards")
	@ResponseBody
	public ResponseEntity<Object> getBoards(@PathVariable("users") int[] id, HttpServletRequest req)
	{

			List<Board> userBoards = new ArrayList<>();
			userBoards = services.getBoardsByUserId(id);
			if(userBoards != null)
			{
				return (ResponseEntity<Object>) userBoards;
			}
			else
			{
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

	}
	
	@GetMapping("/getOneBoard")
	@ResponseBody
	public ResponseEntity<Object> getBoardById(@PathVariable("board.id")int id,HttpServletRequest req)
	{

			Board board = new Board();
			board = services.getBoardByBoardId(id);
			if(board != null)
			{
				List<Board> boards = new ArrayList<>();
				boards.add(board);
				return (ResponseEntity<Object>) boards;
			}
			else
				return new ResponseEntity<>(HttpStatus.CONFLICT);

	}
	
	@GetMapping("/deleteBoard")
	public ResponseEntity<Object> deleteBoard(@PathVariable("board")Board board, HttpServletRequest req)
	{
		if(services != null && board != null && services.getBoardByBoardId(board.getId()) != null) 
		{
			services.deleteBoard(board);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);

	}
	
	@GetMapping("/createBoard")
	@ResponseBody
	public ResponseEntity<Object> createBoard(@PathVariable("id")int id,HttpServletRequest req)
	{

			Board board = new Board();
			board.setId(id);
			board = services.updateBoard(board);
			if(board != null)
			{
				List<Board> list = new ArrayList<>();
				list.add(board);
				return (ResponseEntity<Object>) list;
			}
			else
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		
	}
	
	@GetMapping("/updateBoard")
	@ResponseBody
	public ResponseEntity<Object> updateBoard(@PathVariable("board")Board board,HttpServletRequest req)
	{

			Board updatedBoard = new Board();
			updatedBoard = services.updateBoard(board);
			if(updatedBoard != null)
			{
				List<Board> boards = new ArrayList<>();
				boards.add(updatedBoard);
				return (ResponseEntity<Object>) boards;
			}
			else
				return new ResponseEntity<>(HttpStatus.CONFLICT);

	}
}
