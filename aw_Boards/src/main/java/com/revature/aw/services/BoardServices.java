package com.revature.aw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.aw.dao.Dao;
import com.revature.aw.domain.Board;

@Component("BoardServices")
@Transactional
public class BoardServices
{
	@Autowired
	private Dao dao;
	
	public List<Board> getBoardsByBoardIds(int[] boardIds)
	{
		List<Board> boards = new ArrayList<>();
		for(int i = 0; i < boardIds.length; i++) {
			boards.add(dao.findOne(boardIds[i]));
		}
		return boards;
	}
	
	/**
	 * Service method that calls the Crud Repository to retrieve all boards.
	 * @return A List of all boards in the database.
	 */
	public List<Board> getAllBoards() {
		return dao.findAll();
	}
	
	/**
	 * Service method that calls the Crud Repository to retrieve a specific board by its id.
	 * @param id The id of the board you want to retrieve.
	 * @return A Board with the given id, or null if it doesn't exist.
	 */
	public Board getBoardByBoardId(int id)
	{
		Board board = new Board();
		board = dao.findOne(id);
		return board;
	}
	
	/**
	 * Service method that calls the Crud Repository to create/update a board.
	 * @param board A board that is to be created/updated.
	 * @return The Board that was created/updated.
	 */
	public Board updateBoard(Board board)
	{
		Board updatedBoard = new Board();
		updatedBoard = dao.save(board);
		if(updatedBoard != null)
		{
			return updatedBoard;
		}
		else
			return null;
	}
	
	/**
	 * Service method that calls the Crud Repository to delete a board.
	 * @param board A board that is to be deleted, should have a valid/existing id.
	 */
	public void deleteBoard(Board board)
	{
		dao.delete(board);
	}
}
