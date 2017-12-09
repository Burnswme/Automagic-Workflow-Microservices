package com.revature.aw.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.auth.domain.Login;
import com.revature.aw.auth.service.LoginService;

@RestController
public class AuthCtrl {
	@Autowired
	private LoginService service;

	@PostMapping(value = "/saveLogin")
	public ResponseEntity<Boolean> saveUser(@RequestBody Login user) {
		user = service.saveLogin(user);
		return (user != null) ? new ResponseEntity<>(true, HttpStatus.OK)
				: new ResponseEntity<>(false, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}

}
