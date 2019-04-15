package com.techelevator.model;

import java.util.List;

public interface StudentDAO {
	
	Student editStudent(Student student);
	Student getStudentById(int studentId);
	List<Student> getStudentsbyClassId(int classId);
	void addStudent(Student student, int classId);
	void deleteStudent(int studentId, int classId);

}
