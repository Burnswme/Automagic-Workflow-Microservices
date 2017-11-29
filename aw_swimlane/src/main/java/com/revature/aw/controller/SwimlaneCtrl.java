package com.revature.aw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.Swimlane;
import com.revature.aw.service.SwimlaneService;

@RestController
public class SwimlaneCtrl {
	@Autowired
	private SwimlaneService service;
	
	@PostMapping("/create")
	@ResponseBody
	public ResponseEntity<Object> createSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null) {
			Swimlane sl2 = service.findSwimlaneByBoardIdAndOrder(sl);
			return (sl2 == null) ? new ResponseEntity<>(service.save(sl), HttpStatus.OK)
					: new ResponseEntity<>(sl2, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	//has to check that it actually exists in the db before deleting
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null && service.findSwimlaneById(sl) != null) {
			service.delete(sl);
			System.out.println("Deleted. No issues?");
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

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}
}
