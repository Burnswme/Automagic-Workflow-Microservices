package com.revature.aw.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.aw.domain.Swimlane;

@Repository
public interface SwimlaneDAO extends CrudRepository<Swimlane, Integer> {
	public Swimlane save(Swimlane sl);
	public Swimlane findSwimlaneById(Integer id);
	public void delete(Swimlane sl);	
}
