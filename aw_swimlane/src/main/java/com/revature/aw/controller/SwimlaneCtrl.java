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
	
	/**
	 * Swimlane REST Endpoint to create a swimlane if it doesn't exist
	 * @param sl A swimlane passed in through the Request Body
	 * @param req The HttpRequest
	 * @return A ResponseEntity with the corresponding Swimlane attached to it along with Status OK, or Status CONFLICT if it failed
	 */
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
	
	/**
	 * Swimlane REST Endpoint to delete a swimlane attached to the request body.
	 * @param sl A swimlane passed in through the Request Body, should have a valid id(i.e. it exists in the database).
	 * @param req The HttpRequest
	 * @return A ResponseEntity with true if it succeeded with Status OK, Status CONFLICT if it failed.
	 */
	@PostMapping("/delete")
	public ResponseEntity<Object> deleteSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null && service.findSwimlaneById(sl) != null) {
			service.delete(sl);
			source.storyChannel().send(
					MessageBuilder.withPayload(sl.getId()).build()
				);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else if (service.findSwimlaneById(sl) == null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Swimlane REST Endpoint to update an existing swimlane.
	 * @param sl A swimlane passed in through the Request Body, should have a valid id(i.e. it exists in the database).
	 * @param req The HttpRequest
	 * @return A ResponseEntity with the updated Swimlane if it succeeded with Status OK, Status CONFLICT if it failed
	 */
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<Object> updateSwimlane(@RequestBody Swimlane sl, HttpServletRequest req) {
		if(service != null && sl != null && service.findSwimlaneById(sl) != null) {
			return new ResponseEntity<>(service.save(sl), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Swimlane REST Endpoint to get all the swimlanes of a board given its id.
	 * @param id The id of the board whose swimlanes you want.
	 * @param req The HttpRequest
	 * @return A ResponseEntity with a list of swimlanes attached to a board, can be empty. Status OK if good, Status CONFLICT if bad.
	 */
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
