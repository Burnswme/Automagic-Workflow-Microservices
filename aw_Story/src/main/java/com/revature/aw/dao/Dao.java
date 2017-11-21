package com.revature.aw.dao;

import org.springframework.data.repository.CrudRepository;

import com.revature.aw.domain.Story;

public interface Dao extends CrudRepository<Story,Integer>
{
	//Create, Update
	public Story save(Story story);
	
	//Read
	//Find a Story by its id
	public Story findOne(int id);

	
	//Delete
	public void delete(Story story);
}
