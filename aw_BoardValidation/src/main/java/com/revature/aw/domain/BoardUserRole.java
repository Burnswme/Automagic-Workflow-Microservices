package com.revature.aw.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USER_BOARD_ROLE")
public class BoardUserRole 
{
	@Id
	@SequenceGenerator(name="ubrSeq",sequenceName="UBR_SEQ",allocationSize=1)
	@GeneratedValue(generator="ubrSeq",strategy=GenerationType.SEQUENCE)
	@Column(name = "UBR_ID")
	private int ubrId;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "BD_ID")
	private int boardId;
	
	@Column(name = "ROLE_ID")
	private int roleId;
	
	public BoardUserRole()
	{
		
	}
	
	
	public BoardUserRole(int userId, int boardId, int roleId) 
	{
		super();
		this.userId = userId;
		this.boardId = boardId;
		this.roleId = roleId;
	}
	public BoardUserRole(BoardUserRole bur)
	{
		super();
		this.userId = bur.getUserId();
		this.boardId = bur.getBoardId();
		this.roleId = bur.getRoleId();
	}


	public int getUbrId()
	{
		return ubrId;
	}


	public void setUbrId(int ubrId) 
	{
		this.ubrId = ubrId;
	}


	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId) 
	{
		this.userId = userId;
	}

	public int getBoardId() 
	{
		return boardId;
	}

	public void setBoardId(int boardId) 
	{
		this.boardId = boardId;
	}

	public int getRoleId() 
	{
		return roleId;
	}

	public void setRoleId(int roleId) 
	{
		this.roleId = roleId;
	}

	@Override
	public String toString() 
	{
		return "BoardUserRole [ubrId=" + ubrId + ", userId=" + userId + ", boardId=" + boardId + ", roleId=" + roleId
				+ "]";
	}

	


	
	
}
