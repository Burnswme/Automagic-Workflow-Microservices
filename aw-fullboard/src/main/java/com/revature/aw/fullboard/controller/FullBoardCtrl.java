package com.revature.aw.fullboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.fullboard.domain.Board;
import com.revature.aw.fullboard.service.DataService;

@RestController
public class FullBoardCtrl {
	@Autowired
	private DataService ds;
	
	@GetMapping("/getFullBoardById/{id}")
	@ResponseBody
	public ResponseEntity<Board> getFullBoard(@PathVariable("id") int id, HttpServletRequest req)
	{
		Board board = ds.getBoardById(id);
		return (board != null) ? new ResponseEntity<>(board, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}

}
