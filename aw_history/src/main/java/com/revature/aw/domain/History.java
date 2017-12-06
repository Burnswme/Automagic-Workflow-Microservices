package com.revature.aw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="HISTORY")
public class History {
	@Id
	@SequenceGenerator(name="histSeq", sequenceName="hist_seq", allocationSize=1)
	@GeneratedValue(generator="histSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="HIST_ID")
	private int id;
	
	//id of board user that enacted action
	@Column(name="BU_ID")
	private int userId;
	
	//id of board that action took place in(if any?)
	@Column(name="BD_ID")
	private int boardId;
	
	//name of the action
	@Column(name="HIST_ACTION")
	private String action;
	
	//timestamp of the action
	@Column(name="HIST_TIMESTAMP")
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
