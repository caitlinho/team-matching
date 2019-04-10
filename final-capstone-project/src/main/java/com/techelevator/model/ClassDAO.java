package com.techelevator.model;

import java.util.List;

public interface ClassDAO {

	void addClass(Class newClass);
	List<Class> viewClasses(int instructorId);
	
}
