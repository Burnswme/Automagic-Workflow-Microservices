package com.revature.aw.fullboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.aw.fullboard.domain.Board;
import com.revature.aw.fullboard.domain.Story;
import com.revature.aw.fullboard.domain.Swimlane;
import com.revature.aw.fullboard.domain.Task;

@Service
public class DataService {
	
	RestTemplate rt = new RestTemplate();
	String zuul = "http://localhost:8765";
	
	public Board getBoardById(int id) {
		Board board = this.pollForObject("/aw_boards/getBoard/" + id, Board.class, 5);
		//board.setSwimlanes(this.getSwimlanesByBoardId(board.getId()));
		return board;
	}
	
	public List<Swimlane> getSwimlanesByBoardId(int id) {
		Swimlane[] sls = rt.getForObject(zuul + "/swimlane-service/getSwimlanesByBoardId/" + id, Swimlane[].class);
		ArrayList<Swimlane> swimlanes = new ArrayList<>();
		for (Swimlane sl : sls) {
			sl.setStories(this.getStoriesBySwimlaneId(sl.getId()));
			swimlanes.add(sl);
		}
		return swimlanes;
	}
	
	public List<Story> getStoriesBySwimlaneId(int id) {
		Story[] sts = rt.getForObject(zuul + "/aw_story/getStories/" + id, Story[].class);
		ArrayList<Story> stories = new ArrayList<>();
		for (Story st : sts) {
			st.setTasks(this.getTasksByStoryId(st.getId()));
			stories.add(st);
		}
		return stories;
	}
	
	public List<Task> getTasksByStoryId(int id) {
		Task[] ts = rt.getForObject(zuul + "/tasks/getTasksByStoryId/" + id, Task[].class);
		ArrayList<Task> tasks = new ArrayList<>();
		for (Task t : ts)
			tasks.add(t);
		return tasks;
	}
	
	public <T> T pollForObject(String path, Class<T> responseType, int maxAttempts) {
		int attempts = 0;
		while (attempts < maxAttempts) {
			try {
				T t = rt.getForObject(zuul + path, responseType);
				System.out.println("Reached return: " + t);
				return t;
			} catch (Exception e) {
				System.out.println("Failure on attempt " + ++attempts + ": " + e);
			}
		}
		System.out.println("Max attempts reached; returning null object");
		return null;
	}
}
