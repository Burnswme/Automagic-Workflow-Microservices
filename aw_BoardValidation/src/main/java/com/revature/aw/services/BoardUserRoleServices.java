package com.revature.aw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.aw.dao.Dao;
import com.revature.aw.domain.BoardUserRole;
import com.revature.aw.domain.CompositeKey;

@Component("BoardUserRoleServices")
@Transactional
public class BoardUserRoleServices 
{
	@Autowired
	private Dao dao;
	
	public List<BoardUserRole> findByUserId(int userId) {
		return dao.findByUserId(userId);
	}
	
	public BoardUserRole save(BoardUserRole bur) {
		return dao.save(bur);
	}
	
	public void delete(BoardUserRole bur) {
		dao.delete(bur);
	}
	
	public void deleteByBoardId(int boardId) {
		dao.deleteByBoardId(boardId);
	}
	
	public boolean determinePrivileges(int userId, int boardId) {
		boolean canView = false;
		BoardUserRole bur = new BoardUserRole(0,0,0);
		
		bur = dao.findByUserIdAndBoardId(userId,boardId);
		System.out.println(bur);
		if(bur != null && bur.getRoleId() != 0)
			canView = true;
		return canView;
	}
	
	public BoardUserRole findByUserIdAndBoardId(int userId,int boardId) {
		return dao.findByUserIdAndBoardId(userId, boardId);
	}
}
