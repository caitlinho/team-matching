package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class InstructorClasses {
	
	private int classId;
	
	@NotNull
	private String name;
	
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
