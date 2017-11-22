package com.revature.aw.service;

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
	
	public void delete(Swimlane sl) {
		dao.delete(sl);
	}
}
