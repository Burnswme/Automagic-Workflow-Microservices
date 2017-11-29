package com.revature.aw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.aw.domain.Swimlane;

@Repository
public interface SwimlaneDAO extends CrudRepository<Swimlane, Integer> {
	//creates/updates swimlanes
	public Swimlane save(Swimlane sl);
  
	//finds a swimlane by id
	public Swimlane findSwimlaneById(Integer id);
	
	public Swimlane findSwimlaneByBoardIdAndOrder(int boardId, int order);

	//Find tasks by the story id of the parent story
	public List<Swimlane> findByBoardIdOrderByOrder(int boardId);
  
	//deletes swimlane
	public void delete(Swimlane sl);
}
