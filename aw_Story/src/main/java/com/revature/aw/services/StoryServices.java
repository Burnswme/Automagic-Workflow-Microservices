package com.revature.aw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.revature.aw.dao.Dao;
import com.revature.aw.domain.Story;

@Component("StoryServices")
@Transactional
public class StoryServices
{
	@Autowired
	private Dao dao;
	
	//find a single story by its id
	public Story getStoryByStoryId(int id) {
		return dao.findOne(id);
	}
	//find a group of stories by passing an array of ints
	//provided by the front end
	public List<Story> getStoriesBySwimlaneId(int id)
	{
		return dao.findBySwimlaneIdOrderByOrder(id);
	}
	public Story updateStory(Story story)
	{
		Story returnValue = new Story();
		returnValue = dao.save(story);
		if(returnValue != null)
		{
			return returnValue;
		}
		else
			return null;
	}
	public void deleteStory(Story story)
	{
		dao.delete(story);
	}
	
	public List<Story> removeBySwimlaneId(int swimlaneId) {
		return dao.removeBySwimlaneId(swimlaneId);
	}
	
}
