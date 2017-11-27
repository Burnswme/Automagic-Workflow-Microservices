package com.revature.aw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.Swimlane;
import com.revature.aw.service.SwimlaneService;

@RestController
public class SwimlaneCtrl {
	@Autowired
	private SwimlaneService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<Object> createSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null) {
			return new ResponseEntity<>(service.save(sl), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	//has to check that it actually exists in the db before deleting
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null && service.findSwimlaneById(sl) != null) {
			service.delete(sl);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	//essentially identical to createSwimlane, but has an extra check, checking if the sl id is actually valid/exists
	@PostMapping("/update")
	public ResponseEntity<Object> updateSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		System.out.println("IN UPDATE");
		if(service != null && sl != null && service.findSwimlaneById(sl) != null) {
			System.out.println("UPDATE SL");
			return new ResponseEntity<>(service.save(sl), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PostMapping("/getSwimlanes/{boardId}")
	public ResponseEntity<Object> getSwimlanesByBoardId(@PathVariable("boardId") int id, HttpServletRequest req) {
		if(service != null && id != 0) {
			List<Swimlane> swimlanes = service.findSwimlanesByBoardId(id);
			return new ResponseEntity<>(swimlanes, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
