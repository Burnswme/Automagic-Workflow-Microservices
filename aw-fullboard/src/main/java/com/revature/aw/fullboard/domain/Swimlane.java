package com.revature.aw.fullboard.domain;

import java.util.List;

public class Swimlane {
	private Integer id;
	private int bd_id;
	private String name;
	private int order;
	private List<Story> stories;
	
	public Swimlane() {}

	public Swimlane(Integer id, int bd_id, String name, int order, List<Story> stories) {
		super();
		this.id = id;
		this.bd_id = bd_id;
		this.name = name;
		this.order = order;
		this.stories = stories;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getBd_id() {
		return bd_id;
	}

	public void setBd_id(int bd_id) {
		this.bd_id = bd_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

	@Override
	public String toString() {
		return "Swimlane [id=" + id + ", bd_id=" + bd_id + ", name=" + name + ", order=" + order + ", stories="
				+ stories + "]";
	}
	
}
