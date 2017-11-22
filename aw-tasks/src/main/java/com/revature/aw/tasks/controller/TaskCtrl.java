package com.revature.aw.tasks.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/getTasksByStoryId/{story}")
	@ResponseBody
	public ResponseEntity<List<Task>> getTasksByStoryId(@PathVariable("story") int id, HttpServletRequest req)
	{
		List<Task> tasks = ts.getTasksByStoryId(id);
		return (tasks != null) ? new ResponseEntity<>(tasks, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/getTaskById/{task}")
	@ResponseBody
	public ResponseEntity<Task> getTaskById(@PathVariable("task") int id, HttpServletRequest req)
	{
		Task task = ts.getTaskById(id);
		return (task != null) ? new ResponseEntity<>(task, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PostMapping("/deleteTask")
	public ResponseEntity<Boolean> deleteTask(@RequestBody Task task, HttpServletRequest req)
	{
		ts.deleteTask(task);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/saveTask")
	@ResponseBody
	public ResponseEntity<Object> createBoard(@RequestBody Task task, HttpServletRequest req)
	{
		Task t = ts.saveTask(task);
		return (t != null) ? new ResponseEntity<>(t, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
