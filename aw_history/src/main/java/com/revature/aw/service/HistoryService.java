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
	
	public History createHistory(History hist) {
		return dao.save(hist);
	}
	
	public List<History> getHistoryByBoardId(int boardId) {
		return dao.findHistoryByBoardIdOrderByTimestampDesc(boardId);
	}
}
