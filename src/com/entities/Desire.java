package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "desire")
public class Desire {

	private int id;
	private String name;
	private String email;
	private String desire;

	public Desire() { }

	public Desire(String name, String email, String desire) {
		super();
		this.name = name;
		this.email = email;
		this.desire = desire;
	}
	
	@Id
	@GeneratedValue
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getDesire() {
		return desire;
	}

	public void setDesire(String desire) {
		this.desire = desire;
	}

	@Override
	public String toString() {
		return "Desire [id=" + id + ", name=" + name + ", email=" + email + ", desire=" + desire + "]";
	}
	
}
