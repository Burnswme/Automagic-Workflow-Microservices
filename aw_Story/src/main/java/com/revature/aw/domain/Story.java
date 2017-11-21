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
@Table(name="STORY")
public class Story 
{
	@Id
	@SequenceGenerator(name="storySeq",sequenceName="story_seq", allocationSize=1)
	@GeneratedValue(generator="storySeq",strategy=GenerationType.SEQUENCE)
	@Column(name="ST_ID")
	private int id;
	
	@Column(name="ST_TITLE")
	private String title;
	
	@Column(name="ST_DESC")
	private String description;
	
	@Column(name="ST_POINTS")
	private int points;
	
	@Column(name="ST_COMPLETED")
	private Timestamp dateStoryCompleted;
	
	@Column(name="ST_POSITION")
	private int order;
	
	public Story()
	{
		
	}

	public Story(int id, String title, String description, int points, Timestamp dateStoryCompleted, int order) 
	{
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.points = points;
		this.dateStoryCompleted = dateStoryCompleted;
		this.order = order;
	}
	

	public Story(String title, String description, int points, Timestamp dateStoryCompleted, int order) 
	{
		super();
		this.title = title;
		this.description = description;
		this.points = points;
		this.dateStoryCompleted = dateStoryCompleted;
		this.order = order;
	}
	
	public Story(Story s)
	{
		super();
		this.id = s.getId();
		this.title = s.getTitle();
		this.description = s.getDescription();
		this.points = s.getPoints();
		this.dateStoryCompleted = s.getDateStoryCompleted();
		this.order = s.getOrder();
		
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getPoints() 
	{
		return points;
	}

	public void setPoints(int points) 
	{
		this.points = points;
	}

	public Timestamp getDateStoryCompleted() 
	{
		return dateStoryCompleted;
	}

	public void setDateStoryCompleted(Timestamp dateStoryCompleted) 
	{
		this.dateStoryCompleted = dateStoryCompleted;
	}

	public int getOrder() 
	{
		return order;
	}

	public void setOrder(int order) 
	{
		this.order = order;
	}

	@Override
	public String toString() 
	{
		return "Story [id=" + id + ", title=" + title + ", description=" + description + ", points=" + points
				+ ", dateStoryCompleted=" + dateStoryCompleted + ", order=" + order + "]";
	}
	
	
	
	
	
	
	
}
