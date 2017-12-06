package com.revature.aw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.aw.domain.BoardUserRole;

public interface Dao extends CrudRepository<BoardUserRole,Integer>
{
	//Create
	
	//Read
	public List<BoardUserRole> findByUserId(int userId);
	public BoardUserRole findByUserIdAndBoardId(int userId,int boardId);
	public void deleteByBoardId(int boardId);
	//Update
	public BoardUserRole save(BoardUserRole bur);
	//Delete
	public void delete(BoardUserRole bur);
}
