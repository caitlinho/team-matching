package com.techelevator.model;

import java.util.List;

public interface StudentDAO {
	
	void addStudentList(List<Student> studentList);
	Student editStudent(Student student);
	Student getStudentById(int studentId);
	List<Student> getStudentsbyClassId(int classId);

}
