package com.techelevator.model;

import java.util.List;

public interface InstructorClassesDAO {

	void addClass(InstructorClasses newClass);
	List<InstructorClasses> viewClasses(int instructorId);
	
}
