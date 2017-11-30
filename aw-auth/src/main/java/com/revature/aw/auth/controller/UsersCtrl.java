package com.revature.aw.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.auth.dao.BoardUserDao;
import com.revature.aw.auth.domain.BoardUser;

@RestController
public class UsersCtrl {
	@Autowired
	private BoardUserDao dao;

	@PostMapping(value = "/getUser")
	public ResponseEntity<BoardUser> getUser(@RequestBody BoardUser user) {
		System.out.println("POST /getUser");
		Optional<BoardUser> optUser = dao.findByUsername(user.getUsername());
		optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Exception> handleException(Exception e){
		return new ResponseEntity<Exception>(e,HttpStatus.CONFLICT);
	}

}
