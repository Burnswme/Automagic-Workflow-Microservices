package com.revature.aw.users.dao;

import org.springframework.data.repository.CrudRepository;

import com.revature.aw.users.domain.BoardUser;

public interface BoardUserDao extends CrudRepository<BoardUser, Integer> {			
			//Find a task by the task's id
			public BoardUser findByUsername(String username);
}
