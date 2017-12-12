package com.revature.aw.users.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="BOARD_USER")
public class BoardUser {
	@Id
	@SequenceGenerator(name="buSeq", sequenceName="board_user_seq", allocationSize=1)
	@GeneratedValue(generator="buSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="BU_ID")
	private int id;
	
	@Column(name="BU_USERNAME")
	private String username;
	
	@Column(name="BU_FN")
	private String firstName;
	
	@Column(name="BU_LN")
	private String lastName;
	
	@Column(name="BU_EMAIL")
	private String email;
	
	@Type(type="numeric_boolean")
	@Column(name="BU_ADMIN")
	private boolean isAdmin;
	
	public BoardUser() {}
	
	public BoardUser(BoardUser bu) {
		this.id = bu.id;
		this.username = bu.username;
		this.firstName = bu.firstName;
		this.lastName = bu.lastName;
		this.email = bu.email;
		this.isAdmin = bu.isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "BoardUser [id=" + id + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", isAdmin=" + isAdmin + "]";
	}

}
