package com.revature.aw.auth.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOGIN")
public class Login {
	@Id
	@Column(name="LOGIN_USERNAME")
	private String username;
	
	@Column(name="LOGIN_PASSWORD")
	private String password;
	
	public Login() {}
	
	public Login(Login bu) {
		this.username = bu.username;
		this.password = bu.password;
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

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}

}
