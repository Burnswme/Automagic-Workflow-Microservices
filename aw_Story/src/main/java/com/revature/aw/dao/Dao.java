package com.revature.aw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.aw.domain.Story;

public interface Dao extends CrudRepository<Story,Integer>
{
	//Create, Update
	public Story save(Story story);
	
	//Read
	//Find a Story by its id
	public Story findOne(int id);
	
	//Read
	//Find a Story by its id
	public List<Story> findBySwimlaneId(int swimlaneId);
	public List<Story> findBySwimlaneIdOrderByOrder(int swimlaneId);
	
	//Delete
	public void delete(Story story);
	public List<Story> removeBySwimlaneId(int swimlaneId);
}
