	package com.revature.aw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.aw.dao.HistoryDAO;
import com.revature.aw.domain.History;

@Service
public class HistoryService {
	
	@Autowired
	private HistoryDAO dao;
	/**
	 * Calls the Crud Repository to create a History object.
	 * @param hist A History object to be created.
	 * @return The History object if it was created, null if it failed.
	 */
	public History createHistory(History hist) {
		return dao.save(hist);
	}
	
	/**
	 * Calls the Crud Repository to retrieve a list of history given a board id.
	 * @param boardId The id of the board whose History you want to retrieve.
	 * @return A List of History objects attached to a given board.
	 */
	public List<History> getHistoryByBoardId(int boardId) {
		return dao.findHistoryByBoardIdOrderByTimestampDesc(boardId);
	}
}
