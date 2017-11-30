package com.revature.aw.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2291455638266087750L;

	@Column(name = "BD_ID", nullable = false)
	private int boardId;
	
	@Column(name = "USER_ID", nullable = false)
	private int userId;
	
	public CompositeKey()
	{
		
	}

	public int getBoardId() 
	{
		return boardId;
	}

	public void setBoardId(int boardId) 
	{
		this.boardId = boardId;
	}

	public int getUserId() 
	{
		return userId;
	}

	public void setUserId(int userId) 
	{
		this.userId = userId;
	}

	@Override
	public String toString() 
	{
		return "CompositeKey [boardId=" + boardId + ", userId=" + userId + "]";
	}
	
	
}
