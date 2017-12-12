package com.revature.aw.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.users.domain.BoardUser;
import com.revature.aw.users.service.BoardUserService;

@RestController
public class UsersCtrl {
	@Autowired
	private BoardUserService service;

	@PostMapping(value = "/getUser")
	public ResponseEntity<BoardUser> getUser(@RequestBody BoardUser user) {
		user = service.getUser(user.getUsername());
		return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@PostMapping(value = "/saveUser")
	public ResponseEntity<BoardUser> saveUser(@RequestBody BoardUser user) {
		user = service.saveUser(user);
		return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}

}
