package com.revature.aw.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.History;
import com.revature.aw.service.HistoryService;

@RestController
public class HistoryController {
	
	@Autowired
	private HistoryService service;
	
	@PostMapping("/createHistory")
	public ResponseEntity<Object> createHistory(@RequestBody History hist) {
		if(service != null && hist != null) {
			hist = service.createHistory(hist);
			return new ResponseEntity<>(hist, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/getHistoryByBoardId/{boardId}")
	public ResponseEntity<Object> getHistoryByBoardId(@PathVariable("boardId")int boardId) {
		if(service != null && boardId != 0) {
			List<History> hist = service.getHistoryByBoardId(boardId);
			return new ResponseEntity<>(hist, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
