package com.revature.aw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.dao.SwimlaneDAO;
import com.revature.aw.domain.Swimlane;

@RestController
public class SwimlaneCtrl {
	@Autowired
	private SwimlaneDAO dao;
	
	//dunno what mappings yet
	@PostMapping("/create")
	public Swimlane createSwimlane(@RequestBody Swimlane sl) {
		if(dao != null) {
			return dao.save(sl);
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/delete")
	public void deleteSwimlane(@RequestBody Swimlane sl) {
		if(dao != null && sl != null) {
			dao.delete(sl);
		}
		else {
			System.out.println("some null");
		}
	}
	
	@PostMapping("/update")
	public Swimlane updateSwimlane(@RequestBody Swimlane sl) {
		if(dao != null) {
			return null;
		}
		return null;
	}
}
