package com.revature.aw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.aw.domain.Board;

public interface Dao extends CrudRepository<Board, Integer>
{
	//Create, Update
	//save the changes to the current board
	public Board save(Board board);
	
	//Read
	
	//Find a board by the board's id
	public Board findOne(int id);
	
	//Find a board by the board ids of the user
	public List<Board> findById(int[] ids);
	
	
	//Delete
	//delete the current board
	public void delete(Board board);
}
