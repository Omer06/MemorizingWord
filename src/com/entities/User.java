package com.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "user")
public class User {

	private String username;
	private String password;
	private String email;
	private boolean enable;
	private Roles role;
	private List<Title> titles = new ArrayList<Title>();

	public User() { }
	
	public User(String username, String password, boolean enable, Roles role , List<Title> titles) {
		super();
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.role = role;
		this.titles = titles;
	}

	@Id
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

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@OneToOne
	@JoinColumn(name = "roleId")
	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
	@OneToMany
	@JoinColumn(name = "username")
	public List<Title> getTitles() {
		return titles;
	}
	
	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
