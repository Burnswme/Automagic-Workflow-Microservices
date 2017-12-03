package com.revature.aw.controller;

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

import com.revature.aw.domain.Swimlane;
import com.revature.aw.message.SwimlaneSource;
import com.revature.aw.service.SwimlaneService;

@RestController
public class SwimlaneCtrl {
	@Autowired
	private SwimlaneService service;
	@Autowired
	private SwimlaneSource source;
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Object> createSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null) {
			Swimlane sl2 = service.findSwimlaneByBoardIdAndOrder(sl);
			return (sl2 == null) ? new ResponseEntity<>(service.save(sl), HttpStatus.OK)
					: new ResponseEntity<>(sl2, HttpStatus.OK);
		}
		System.out.println("CONFLICT?");
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	//has to check that it actually exists in the db before deleting
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null && service.findSwimlaneById(sl) != null) {
			service.delete(sl);
			source.storyChannel().send(
					MessageBuilder.withPayload(sl.getId()).build()
				);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else if (service.findSwimlaneById(sl) == null) {
			System.out.println("Not here, so good?");
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	//essentially identical to createSwimlane, but has an extra check, checking if the sl id is actually valid/exists
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<Object> updateSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null && service.findSwimlaneById(sl) != null) {
			return new ResponseEntity<>(service.save(sl), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/getSwimlanesByBoardId/{boardId}")
	@ResponseBody
	public ResponseEntity<Object> getSwimlanesByBoardId(@PathVariable("boardId") int id, HttpServletRequest req) {
		if(service != null && id != 0) {
			List<Swimlane> swimlanes = service.findSwimlanesByBoardId(id);
			return new ResponseEntity<>(swimlanes, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/getOtherSwimlanes/{boardId}/{swimlaneId}")
	@ResponseBody
	public ResponseEntity<Object> getOtherSwimlanes(@PathVariable("boardId") int boardId, @PathVariable("swimlaneId") int swimlaneId) {
		if(service != null && boardId != 0 && swimlaneId != 0) {
			List<Swimlane> filteredSwimlanes = service.findOtherSwimlanes(boardId, swimlaneId);
			return new ResponseEntity<>(filteredSwimlanes, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
}
