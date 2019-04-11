package com.techelevator.model;

public interface StudentDAO {
	
	void addStudentList();
	Student editStudent(Student student);
	Student getStudentById(int studentId);

}
