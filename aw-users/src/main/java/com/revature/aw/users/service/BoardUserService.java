package com.revature.aw.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.aw.users.dao.BoardUserDao;
import com.revature.aw.users.domain.BoardUser;

@Service
public class BoardUserService {
	
	@Autowired
	private BoardUserDao dao;
	
	public BoardUser getUser(String username) {
		return dao.findByUsername(username);
	}
	
	public BoardUser saveUser(BoardUser user) {
		return dao.save(user);
	}
}