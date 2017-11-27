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
	
	public List<Swimlane> findSwimlanesByBoardId(int id) {
		return dao.findByBoardId(id);
	}
	
	public void delete(Swimlane sl) {
		dao.delete(sl);
	}
	
	public List<Swimlane> findSwimlanesByBoardId(int boardId) {
		return dao.findByBoardId(boardId);
	}
}
