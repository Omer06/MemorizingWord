package com.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "roles")
public class Roles {

	private int id;
	private String role;

	public Roles() { }

	public Roles(String role) {
		super();
		this.role = role;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
