package com.revature.aw.fullboard.domain;

import java.sql.Timestamp;
import java.util.List;

public class Board {
	private int id;
	private String name;
	private Timestamp startDate;
	private int numDays;
	private List<Swimlane> swimlanes;
	
	public Board() {}

	public Board(int id, String name, Timestamp startDate, int numDays, List<Swimlane> swimlanes) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.numDays = numDays;
		this.swimlanes = swimlanes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public List<Swimlane> getSwimlanes() {
		return swimlanes;
	}

	public void setSwimlanes(List<Swimlane> swimlanes) {
		this.swimlanes = swimlanes;
	}

	@Override
	public String toString() {
		return "Board [id=" + id + ", name=" + name + ", startDate=" + startDate + ", numDays=" + numDays
				+ ", swimlanes=" + swimlanes + "]";
	}

}
