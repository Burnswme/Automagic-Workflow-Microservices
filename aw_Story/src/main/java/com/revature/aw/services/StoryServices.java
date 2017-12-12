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
	
	/**
	 * Service method used to get a story directly by its id.
	 * @param id The id of the story you want to retrieve.
	 * @return A Story Object with the corresponding id.
	 */
	public Story getStoryByStoryId(int id) {
		return dao.findOne(id);
	}
	
	/**
	 * Service method used to retrieve a list of stories attached to a swimlane.
	 * @param id The id of the swimlane whose stories you want to retrieve.
	 * @return A List of Stories attached to the swimlane id.
	 */
	public List<Story> getStoriesBySwimlaneId(int id) {
		return dao.findBySwimlaneIdOrderByOrder(id);
	}
	
	/**
	 * Service method used to save/update a story.
	 * @param story A story to be saved/updated.
	 * @return The story that was saved/updated.
	 */
	public Story updateStory(Story story) {
		Story returnValue = new Story();
		returnValue = dao.save(story);
		if (returnValue != null) {
			return returnValue;
		} else
			return null;
	}
	
	/**
	 * Service method used to delete a story.
	 * @param story A story to be deleted. Should have a valid id(i.e. it exists in the database).
	 */
	public void deleteStory(Story story) {
		dao.delete(story);
	}
	
	/**
	 * Service method used to remove all stories attached to a swimlane. Used in conjunction with RabbitMQ to do cascading deletes.
	 * @param swimlaneId Id of the swimlane whose stories you want to delete.
	 * @return A List of all Stories removed.
	 */
	public List<Story> removeBySwimlaneId(int swimlaneId) {
		return dao.removeBySwimlaneId(swimlaneId);
	}
	
}
