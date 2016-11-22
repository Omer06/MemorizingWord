package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "word")
public class Word {

	private int id;
	private String name;
	private String meaning;
	private String reminding;
	private String guess;

	public Word() { }

	public Word(String name, String meaning, String reminding, String guess) {
		super();
		this.name = name;
		this.meaning = meaning;
		this.reminding = reminding;
		this.guess = guess;
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

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getReminding() {
		return reminding;
	}

	public void setReminding(String reminding) {
		this.reminding = reminding;
	}

	@Transient
	public String getGuess() {
		return guess;
	}

	public void setGuess(String guess) {
		this.guess = guess;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", name=" + name + ", meaning=" + meaning + ", reminding=" + reminding + ", guess="
				+ guess + "]";
	}

	@Override
	public int hashCode() {
		return this.meaning.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return ((Word)obj).getMeaning().equals(this.meaning);
	}
	
	
	
	

}
