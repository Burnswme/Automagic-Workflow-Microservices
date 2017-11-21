package com.revature.aw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BOARD_USER")
public class BoardUser {
	@Id
	@SequenceGenerator(name="buSeq", sequenceName="board_user_seq", allocationSize=1)
	@GeneratedValue(generator="buSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="BU_ID")
	private Integer id;
	
	@Column(name="BU_USERNAME")
	private String username;
	
	@Column(name="BU_PASSWORD")
	private String password;
	
	@Column(name="BU_FN")
	private String firstName;
	
	@Column(name="BU_LN")
	private String lastName;
	
	@Column(name="BU_EMAIL")
	private String email;
	
	public BoardUser() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "BoardUser [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}

	
	
	
}
