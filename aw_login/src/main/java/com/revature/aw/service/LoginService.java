package com.revature.aw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.dao.LoginDAO;
import com.revature.aw.domain.BoardUser;

@Component
public class LoginService {
	@Autowired
	private LoginDAO dao;
	
	//BoardUser will be sent in as a partial user, with just Username and Password
	//if it passes the check, return the whole user, otherwise return null
	public BoardUser login(BoardUser bu) {
		if(dao != null && bu != null) {
			BoardUser check = dao.getBoardUserByUsername(bu.getUsername());
			if(check != null && bu.getPassword().equals(check.getPassword())) {
				return check;
			}
		}
		return null;
	}
	
	public BoardUser register(BoardUser bu) {
		return dao.save(bu);
	}
}
