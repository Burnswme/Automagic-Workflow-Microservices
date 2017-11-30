package com.revature.aw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.BoardUser;
import com.revature.aw.service.LoginService;

@RestController
public class LoginCtrl {

	@Autowired
	private LoginService service;
	
	@PostMapping("/login")
	public ResponseEntity<BoardUser> login(@RequestBody BoardUser bu, HttpServletRequest req) {
		if(service != null && bu != null) {
			bu = service.login(bu);
			if(bu != null) {
				HttpSession session = req.getSession();
				session.setAttribute("user", bu);
				//so other classes can just get a basic int to use user ID, rather than having to import/create boardUser as a whole
				session.setAttribute("userId", bu.getId());
				//same with username, otherwise we'd need to import BoardUser onto every microservice
				session.setAttribute("userUsername", bu.getUsername());
				
				return new ResponseEntity<>(bu, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}