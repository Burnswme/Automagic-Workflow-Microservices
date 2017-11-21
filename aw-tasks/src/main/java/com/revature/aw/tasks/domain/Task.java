package com.revature.aw.tasks.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="TASK")
public class Task {
	@Id
	@SequenceGenerator(name="taskSeq", sequenceName="task_seq", allocationSize=1)
	@GeneratedValue(generator="taskSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="TSK_ID")
	private int id;
	
	@Column(name="ST_ID")
	private int storyId;
	
	@Column(name="TSK_NAME")
	private String name;
	
	@Type(type="numeric_boolean")
	@Column(name="TSK_COMPLETED")
	private boolean completed;
	
	@Column(name="TSK_POSITION")
	private int order;
	
	public Task() {}

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
