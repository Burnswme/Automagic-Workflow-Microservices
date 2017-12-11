package com.revature.aw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.aw.dao.Dao;
import com.revature.aw.domain.BoardUserRole;

@Component("BoardUserRoleServices")
@Transactional
public class BoardUserRoleServices 
{
	@Autowired
	private Dao dao;
	/**
	 * Service method to find a list of boards a User can view.
	 * @param userId The id of the User whose boards you want.
	 * @return A List of BoardUserRoles, essentially a list of Boards the User can view.
	 */
	public List<BoardUserRole> findByUserId(int userId) {
		return dao.findByUserId(userId);
	}
	
	/**
	 * Service method to save a new BoardUserRole. Used to add a new User to a Board.
	 * @param bur The BoardUserRole to be added.
	 * @return The saved BoardUserRole.
	 */
	public BoardUserRole save(BoardUserRole bur) {
		return dao.save(bur);
	}
	
	/**
	 * Service method to delete a User from a Board.
	 * @param bur A BUR object with the user and board to be deleted from.
	 */
	public void delete(BoardUserRole bur) {
		dao.delete(bur);
	}
	
	/**
	 * Service method to delete all the Users attached to a board.
	 * @param boardId Board to be deleted.
	 */
	public void deleteByBoardId(int boardId) {
		dao.deleteByBoardId(boardId);
	}
	
	/**
	 * Service method to determine if a User can access a specific Board.
	 * @param userId The User to check.
	 * @param boardId The Board to check on.
	 * @return True if the User can access the Board. False if they can't.
	 */
	public boolean determinePrivileges(int userId, int boardId) {
		boolean canView = false;
		BoardUserRole bur = new BoardUserRole(0,0,0);
		
		bur = dao.findByUserIdAndBoardId(userId,boardId);
		if(bur != null && bur.getRoleId() != 0)
			canView = true;
		return canView;
	}
	
	/**
	 * Service method to determine if a User can access a specific Board.
	 * @param userId The User to check.
	 * @param boardId The Board to check on.
	 * @return A BUR object if valid, null if not.	
	 */
	public BoardUserRole findByUserIdAndBoardId(int userId,int boardId) {
		return dao.findByUserIdAndBoardId(userId, boardId);
	}
}
