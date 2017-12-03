package com.revature.aw.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.aw.tasks.domain.Task;
import com.revature.aw.tasks.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AwTasksApplicationTests {
	
	@Autowired
	private TaskService ts;
	
	Task task;

	@Test
	public void contextLoads() {
	}
	
	@Before
	public void createTask() {
		task = new Task(0, 999, "A test task", false, 1);
		task = ts.saveTask(task);
	}
	
	@Test
	public void getTask() {
		System.out.println("In getTask: " + task);
		task = ts.getTaskById(task.getId());
		assertNotNull(task);
	}
	
	@Test
	public void getTasksById() {
		System.out.println("In getTasks: " + task);
		ArrayList<Task> tl1 = new ArrayList<>();
		tl1.add(task);
		ArrayList<Task> tl2 = (ArrayList<Task>) ts.getTasksByStoryId(999);
		assertEquals(tl1, tl2);
	}
	
	@Test
	public void updateTask() {
		System.out.println("In updateTask: " + task);
		Task t = task;
		t.setName("A different name");
		task = ts.saveTask(t);
		assertNotNull(task);
		assertEquals(t, task);
	}
	
	@Test
	public void deleteTask() {
		System.out.println("In deleteTask: " + task);
		ts.deleteTask(task);
		task = ts.getTaskById(task.getId());
		assertNull(task);
	}
	

}
