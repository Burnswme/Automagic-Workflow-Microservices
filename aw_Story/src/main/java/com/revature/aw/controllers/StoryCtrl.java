package com.revature.aw.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.aw.domain.Story;
import com.revature.aw.services.StoryServices;

@RestController
public class StoryCtrl 
{
	@Autowired
	private StoryServices service;
	
	@GetMapping("/getStories/{id}")
	@ResponseBody
	public ResponseEntity<Object> getStories(@PathVariable("id")int id, HttpServletRequest req) {
		List<Story> list = service.getStoriesBySwimlaneId(id);
		return (list != null) ? new ResponseEntity<>(list, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@GetMapping("/getOneStory")
	@ResponseBody
	public ResponseEntity<Object> getSingleStory(@PathVariable("story") Story story, HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		if(session != null)
		{
			List<Story> list = new ArrayList();
			Story st = new Story();
			st = service.getStoryByStoryId(story.getId());
			if(st != null)
			{
				list.add(st);
				return (ResponseEntity<Object>) list;
			}
			else
				return null;
		}
		else
			return null;
	}
	@GetMapping("/createStory")
	public void createStory(@PathVariable("story")Story story, HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		if(session != null)
		{
			Story st = new Story();
			st = service.updateStory(story);
		}
		else
			System.out.println("Null Session -StoryCtrl -GET /createStory");
	}
	@GetMapping("/deleteStory")
	public void deleteStory(@PathVariable("story")Story story, HttpServletRequest req)
	{
		HttpSession session = req.getSession();
		if(session != null)
		{
			service.deleteStory(story);
		}
		else
			System.out.println("Null Session -StoryCtrl -GET /deleteStory");
	}

}
