package com.revature.aw.fullboard.domain;

public class Task {
	private int id;
	private int storyId;
	private String name;
	private boolean completed;
	private int order;
	
	public Task() {}

	public Task(int id, int storyId, String name, boolean completed, int order) {
		super();
		this.id = id;
		this.storyId = storyId;
		this.name = name;
		this.completed = completed;
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", storyId=" + storyId + ", name=" + name + ", completed=" + completed + ", order="
				+ order + "]";
	}

}
