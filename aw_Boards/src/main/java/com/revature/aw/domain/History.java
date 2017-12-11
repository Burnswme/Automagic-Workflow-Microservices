package com.revature.aw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


public class History {

	private int id;
	
	//id of board user that enacted action
	private int userId;
	
	//id of board that action took place in(if any?)
	private int boardId;
	
	//name of the action
	private String action;
	
	//timestamp of the action
	private Date timestamp;
	
	public History() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", userId=" + userId + ", boardId=" + boardId + ", action=" + action
				+ ", timestamp=" + timestamp + "]";
	}

	
	
	
	
}
