package com.revature.aw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SWIMLANE")
public class Swimlane {
	@Id
	@SequenceGenerator(name="slSeq", sequenceName="swimlane_seq", allocationSize=1)
	@GeneratedValue(generator="slSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="SL_ID")
	private Integer id;
	
	@Column(name="SL_NAME")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Swimlane [id=" + id + ", name=" + name + "]";
	}
	
	
}
