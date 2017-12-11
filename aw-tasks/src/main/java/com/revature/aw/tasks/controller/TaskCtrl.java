package com.revature.aw.tasks.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.tasks.domain.Task;
import com.revature.aw.tasks.service.TaskService;

@RestController
public class TaskCtrl {
	@Autowired
	private TaskService ts;
	
	/**
	 * Task REST Endpoint to retrieve all tasks attached to a specific story.
	 * @param id Id of the story whose tasks you want to retrieve. Should be in the path of the url request
	 * @param req The HttpRequest.
	 * @return A ResponseEntity object that contains the list of tasks as well as a Status OK. Status CONFLICT if it fails.
	 */
	@GetMapping("/getTasksByStoryId/{id}")
	@ResponseBody
	public ResponseEntity<List<Task>> getTasksByStoryId(@PathVariable("id") int id, HttpServletRequest req) {
		List<Task> tasks = ts.getTasksByStoryId(id);
		return (tasks != null) ? new ResponseEntity<>(tasks, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/**
	 * Task REST Endpoint to get task by a specific id.
	 * @param id The id of the task you want to retrieve.
	 * @param req The HttpRequest.
	 * @return A ResponseEntity object that contains the task as well a Status OK. Status CONFLICT if it fails.
	 */
	@GetMapping("/getTaskById/{id}")
	@ResponseBody
	public ResponseEntity<Task> getTaskById(@PathVariable("id") int id, HttpServletRequest req)	{
		Task task = ts.getTaskById(id);
		return (task != null) ? new ResponseEntity<>(task, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/**
	 * Task REST Endpoint to delete a specific task.
	 * @param task A Task object attached to the Request Body, should have a valid id(i.e. it exists in the database).
	 * @param req The HttpRequest.
	 * @return A ResponseEntity object that contains True as well as a Status OK. Status CONFLICT if it fails.
	 */
	@PostMapping("/deleteTask")
	public ResponseEntity<Boolean> deleteTask(@RequestBody Task task, HttpServletRequest req) {
		if(ts != null && task != null && ts.getTaskById(task.getId()) != null) {
			ts.deleteTask(task);
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else if (ts.getTaskById(task.getId()) == null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	/**
	 * Task REST Endpoint to save/create a new task.
	 * @param task A Task object attached to the Request Body. Should have 0/null for id, as it should not currently exist.
	 * @param req The HttpRequest.
	 * @return A ResponseEntity object that contains True as well as a Status OK. Status CONFLICT if it fails.
	 */
	@PostMapping("/saveTask")
	@ResponseBody
	public ResponseEntity<Object> createTask(@RequestBody Task task, HttpServletRequest req) {
		Task t = ts.saveTask(task);
		return (t != null) ? new ResponseEntity<>(t, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/**
	 * Task REST Endpoint to update an existing task.
	 * @param task A Task object attached to the Request Body. Should have a valid id(i.e. it should exist within the database).
	 * @param req The HttpRequest.
	 * @return A ResponseEntity Object that contains the corresponding task as well as a Status OK. Status CONFLICT if it fails.
	 */
	@PostMapping("/updateTask")
	public ResponseEntity<Object> updateTask(@RequestBody Task task, HttpServletRequest req) {
		Task t = ts.saveTask(task);
		return (t != null) ? new ResponseEntity<>(t, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
