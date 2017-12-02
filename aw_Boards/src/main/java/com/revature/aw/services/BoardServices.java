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
	
	public List<Board> getBoardsByUserId(int[] boardIds)
	{
		List<Board> boards = new ArrayList<>();
		for(int i = 0; i < boardIds.length; i++)
		{
			Board b = new Board();
			b = dao.findOne(boardIds[i]);
			boards.add(b);
		}
		if(!boards.isEmpty())
		{
			return boards;
		}
		else
			return null;
	}
	public Board getBoardByBoardId(int id)
	{
		Board board = new Board();
		board = dao.findOne(id);
		return board;
	}
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
	public void deleteBoard(Board board)
	{
		dao.delete(board);
	}
}
