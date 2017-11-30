package com.revature.aw.tasks.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.aw.tasks.domain.Task;
import com.revature.aw.tasks.service.TaskService;

@RestController
public class TaskCtrl {
	@Autowired
	private TaskService ts;
	
	@GetMapping("/getTasksByStoryId/{id}")
	@ResponseBody
	public ResponseEntity<List<Task>> getTasksByStoryId(@PathVariable("id") int id, HttpServletRequest req) {
		List<Task> tasks = ts.getTasksByStoryId(id);
		return (tasks != null) ? new ResponseEntity<>(tasks, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/getTaskById/{id}")
	@ResponseBody
	public ResponseEntity<Task> getTaskById(@PathVariable("id") int id, HttpServletRequest req)	{
		Task task = ts.getTaskById(id);
		return (task != null) ? new ResponseEntity<>(task, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
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
	
	@PostMapping("/saveTask")
	@ResponseBody
	public ResponseEntity<Object> createTask(@RequestBody Task task, HttpServletRequest req) {
		Task t = ts.saveTask(task);
		return (t != null) ? new ResponseEntity<>(t, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	//mostly for AoP/history/logging
	@PostMapping("/updateTask")
	public ResponseEntity<Object> updateTask(@RequestBody Task task, HttpServletRequest req) {
		System.out.println(task);
		Task t = ts.saveTask(task);
		return (t != null) ? new ResponseEntity<>(t, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}
