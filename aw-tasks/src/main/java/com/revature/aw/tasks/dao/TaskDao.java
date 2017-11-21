package com.revature.aw.tasks.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.aw.tasks.domain.Task;

public interface TaskDao extends CrudRepository<Task, Integer> {
	//Create, Update
		//save the changes to the current task
		public Task save(Task task);
		
		//Find a task by the task's id
		public Task findOne(int id);
		
		//Find tasks by the story id of the parent story
		public List<Task> findByStoryId(int storyId);
		
		//Delete
		//delete the current task
		public void delete(Task task);
}
