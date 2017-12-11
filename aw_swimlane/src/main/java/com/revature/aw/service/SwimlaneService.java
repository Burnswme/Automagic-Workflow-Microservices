package com.revature.aw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.aw.dao.SwimlaneDAO;
import com.revature.aw.domain.Swimlane;

@Component
public class SwimlaneService {
	
	@Autowired
	private SwimlaneDAO dao;
	
	/**
	 * Calls the Crud Repository to try and save/update a swimlane to the database
	 * Returns null if unsuccessful
	 * @param sl The swimlane to be saved/updated.
	 * @return The swimlane saved/updated, if success, otherwise null.
	 */
	public Swimlane save(Swimlane sl) {
		return dao.save(sl);
	}
	
	/**
	 * Calls the Crud Repository to try and find a swimlane directly by its id(Primary key)
	 * @param sl A swimlane object with an ID attached to it
	 * @return The swimlane found, if it exists, or null
	 */
	public Swimlane findSwimlaneById(Swimlane sl) {
		return dao.findSwimlaneById(sl.getId());
	}
	
	/**
	 * Calls the Crud Repository to try and find a swimlane by the board it's attached to and its
	 * positioning/order in the board.
	 * @param sl A swimlane object with a valid boardId and order.
	 * @return The swimlane found, if it exists, or null.
	 */
	public Swimlane findSwimlaneByBoardIdAndOrder(Swimlane sl) {
		return dao.findSwimlaneByBoardIdAndOrder(sl.getBoardId(), sl.getOrder());
	}
	
	/**
	 * Calls the Crud Repository to try and find all the swimlanes attached to a board.
	 * @param id The id of the board whose swimlanes you want to retrieve
	 * @return A list of swimlanes attached to a board, can be empty.
	 */
	public List<Swimlane> findSwimlanesByBoardId(int id) {
		return dao.findByBoardIdOrderByOrder(id);
	}
	
	/**
	 * Calls the Crud Repository to try and delete a swimlane.
	 * @param sl The swimlane with a valid id that you want to delete.
	 */
	public void delete(Swimlane sl) {
		dao.delete(sl);
	}
	
	/**
	 * Calls the Crud Repository to try and remove all swimlanes attached to a board Id. Used in conjunction with RabbitMQ.
	 * @param boardId The board whose swimlanes you want to remove
	 * @return A list of all swimlanes removed.
	 */
	public List<Swimlane> removeByBoardId(int boardId) {
		return dao.removeByBoardId(boardId);
	}
}
