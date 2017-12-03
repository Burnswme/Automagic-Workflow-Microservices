package com.revature.aw.fullboard.domain;

import java.sql.Timestamp;
import java.util.List;

public class Story {
	private int id;
	private int swimlaneId;
	private String title;
	private String description;
	private int points;
	private Timestamp dateStoryCompleted;
	private int order;
	private List<Task> tasks;
	
	public Story() {}

	public Story(int id, int swimlaneId, String title, String description, int points, Timestamp dateStoryCompleted,
			int order, List<Task> tasks) {
		super();
		this.id = id;
		this.swimlaneId = swimlaneId;
		this.title = title;
		this.description = description;
		this.points = points;
		this.dateStoryCompleted = dateStoryCompleted;
		this.order = order;
		this.tasks = tasks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSwimlaneId() {
		return swimlaneId;
	}

	public void setSwimlaneId(int swimlaneId) {
		this.swimlaneId = swimlaneId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Timestamp getDateStoryCompleted() {
		return dateStoryCompleted;
	}

	public void setDateStoryCompleted(Timestamp dateStoryCompleted) {
		this.dateStoryCompleted = dateStoryCompleted;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", swimlaneId=" + swimlaneId + ", title=" + title + ", description=" + description
				+ ", points=" + points + ", dateStoryCompleted=" + dateStoryCompleted + ", order=" + order + ", tasks="
				+ tasks + "]";
	}

}
