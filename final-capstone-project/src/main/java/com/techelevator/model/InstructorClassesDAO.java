package com.techelevator.model;

import java.util.List;

public interface InstructorClassesDAO {

	void addClass(InstructorClasses newClass, int id);
	
	List<InstructorClasses> viewClasses(int id);
	
}
