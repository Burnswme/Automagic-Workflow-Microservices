package com.revature.aw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		HttpSession session = req.getSession(false);
		if(session != null)
		{
			List<Board> userBoards = new ArrayList<>();
			userBoards = services.getBoardsByUserId(id);
			if(userBoards != null)
			{
				return (ResponseEntity<Object>) userBoards;
			}
			else
			{
				return null;
			}
			
		}
		else
			return null;
	}
	
	@GetMapping("/getOneBoard")
	@ResponseBody
	public ResponseEntity<Object> getBoardById(@PathVariable("board.id")int id,HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		if(session != null)
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
				return null;
		}
		else
			return null;
	}
	
	@GetMapping("/deleteBoard")
	public void deleteBoard(@PathVariable("board")Board board, HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		if(session != null)
		{
			services.deleteBoard(board);
		}
		else
			System.out.println("Null Session -deleteBoard");
		
	}
	
	@GetMapping("/createBoard")
	@ResponseBody
	public ResponseEntity<Object> createBoard(@PathVariable("id")int id,HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		if(session != null)
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
				return null;
			
		}
		else
			return null;
		
		
	}
	
	@GetMapping("/updateBoard")
	@ResponseBody
	public ResponseEntity<Object> updateBoard(@PathVariable("board")Board board,HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		if(session != null)
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
				return null;
		}
		else
			return null;
	}
}
