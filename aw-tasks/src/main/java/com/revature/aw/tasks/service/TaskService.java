package com.revature.aw.tasks.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.aw.tasks.dao.TaskDao;
import com.revature.aw.tasks.domain.Task;

@Service
@Transactional
public class TaskService {
	@Autowired
	private TaskDao dao;
	/**
	 * Service method to call the Crud Repository to get all the tasks associated with a given story.
	 * @param storyId Id of the story whose tasks you want to retrieve.
	 * @return A List of Tasks associated with the story id that you passed.
	 */
	public List<Task> getTasksByStoryId(int storyId) {
		return dao.findByStoryIdOrderByOrder(storyId);
	}
	
	/**
	 * Service method to call the Crud Repository getting a task directly by its id.
	 * @param id The id of the task you want to retrieve.
	 * @return A Task with the id you passed in.
	 */
	public Task getTaskById(int id) {
		return dao.findOne(id);
	}
	
	/**
	 * Service method that calls the Crud Repository to either create/update a Task.
	 * @param task A Task you want to create/update.
	 * @return The Task that was created/updated.
	 */
	public Task saveTask(Task task) {
		return dao.save(task);
	}
	
	/**
	 * Service method that calls the Crud Repository to delete a task that you pass in as a parameter.
	 * @param task A Task with a valid id(i.e. it exists in the database) to be deleted.
	 */
	public void deleteTask(Task task) {
		dao.delete(task);
	}
	
	/**
	 * Service method that calls the Crud Repository to delete all tasks associated with a given story id. Used in conjunction with RabbitMQ for cascade deletes.
	 * @param storyId The story id whose tasks you want to delete.
	 */
	public void deleteByStoryId(int storyId) {
		dao.deleteByStoryId(storyId);
	}
}
