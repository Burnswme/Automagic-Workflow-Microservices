package com.revature.aw.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BOARD")
public class Board 
{
	@Id
	@SequenceGenerator(name="boardSeq", sequenceName="board_seq", allocationSize=1)
	@GeneratedValue(generator="boardSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="BD_ID")
	private int id;
	
	@Column(name="BD_NAME")
	private String name;
	
	@Column(name="BD_STARTDATE")
	private Timestamp startDate;
	
	@Column(name="BD_DURATION")
	private int numDays;
	
	public Board()
	{
		
	}
	

	public Board(int id, String name, Timestamp startDate, int numDays) 
	{
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.numDays = numDays;
	}
	public Board(String name, Timestamp startDate, int numDays)
	{
		super();
		this.name = name;
		this.startDate = startDate;
		this.numDays = numDays;
	}
	public Board(Board bd)
	{
		super();
		this.id = bd.getId();
		this.name = bd.getName();
		this.startDate = bd.getStartDate();
		this.numDays = bd.getNumDays();
	}


	public int getId()
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Timestamp getStartDate() 
	{
		return startDate;
	}

	public void setStartDate(Timestamp startDate) 
	{
		this.startDate = startDate;
	}

	public int getNumDays() 
	{
		return numDays;
	}

	public void setNumDays(int numDays) 
	{
		this.numDays = numDays;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", name=" + name + ", startDate=" + startDate + ", numDays=" + numDays + "]";
	}
}
