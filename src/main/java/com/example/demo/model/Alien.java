package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Alien {
	@Id
	private int id;
	private String name;
	
	public int getAid() {
		return id;
	}
	public void setAid(int aid) {
		this.id = aid;
	}
	public String getAname() {
		return name;
	}
	public void setAname(String aname) {
		this.name = aname;
	}
	
	@Override
	public String toString() {
		return "Alien [aid=" + id + ", aname=" + name + "]";
	}
	
	
}
