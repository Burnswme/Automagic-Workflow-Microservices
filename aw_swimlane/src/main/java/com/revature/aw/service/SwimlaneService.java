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
	
	//create and update
	public Swimlane save(Swimlane sl) {
		return dao.save(sl);
	}
	
	public Swimlane findSwimlaneById(Swimlane sl) {
		return dao.findSwimlaneById(sl.getId());
	}
	
	public Swimlane findSwimlaneByBoardIdAndOrder(Swimlane sl) {
		return dao.findSwimlaneByBoardIdAndOrder(sl.getBoardId(), sl.getOrder());
	}

	public List<Swimlane> findSwimlanesByBoardId(int id) {
		return dao.findByBoardIdOrderByOrder(id);
	}
	
	public void delete(Swimlane sl) {
		dao.delete(sl);
	}
	
	public List<Swimlane> removeByBoardId(int boardId) {
		return dao.removeByBoardId(boardId);
	}
	
	//returns all swimlanes of a board except the current one
	//
	public List<Swimlane> findOtherSwimlanes(int boardId, int swimlaneId) {
		List<Swimlane> filter = dao.findByBoardId(boardId);
		for(int i = 0; i < filter.size(); i++) {
			if(filter.get(i).getId() == swimlaneId) {
				filter.remove(i);
				return filter;
			}
		}
		return null;
	}
}
