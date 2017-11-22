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
	
	public List<Task> getTasksByStoryId(int storyId) {
		return dao.findByStoryId(storyId);
	}
	
	public Task getTaskById(int id) {
		return dao.findOne(id);
	}
	
	public Task saveTask(Task task) {
		return dao.save(task);
	}
	
	public void deleteTask(Task task) {
		dao.delete(task);
	}
}
