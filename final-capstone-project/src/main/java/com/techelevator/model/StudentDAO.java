package com.techelevator.model;

import java.util.List;

public interface StudentDAO {
	
	void addStudentList();
	Student editStudent(Student student);
	Student getStudentById(int studentId);
	List<Student> getStudentsbyClassId(int classId);

}
