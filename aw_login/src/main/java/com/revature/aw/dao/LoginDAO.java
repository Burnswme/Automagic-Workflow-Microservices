package com.revature.aw.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.aw.domain.BoardUser;

@Repository
public interface LoginDAO extends CrudRepository<BoardUser, Integer>{
	public BoardUser getBoardUserByUsername(String username);
	
	public BoardUser save(BoardUser user);
}
