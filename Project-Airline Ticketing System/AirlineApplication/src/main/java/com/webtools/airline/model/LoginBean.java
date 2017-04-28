package com.webtools.airline.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class LoginBean {

	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String pass;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	
}
