package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCStudentDAO implements StudentDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCStudentDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//need to fix
	@Override
	public void addStudentList() {
	String sqlToAddStudentsList = "COPY student (student_id, name, email, comments) FROM '/Users/gsutter/Development/capstone/final-capstone-team-bravo/studentList.csv' DELIMITERS ',' CSV header FORCE QUOTE *;\n"; 
	jdbcTemplate.update(sqlToAddStudentsList);
	 
			
	}
	
	@Override
	public Student getStudentById(int studentId) {
		Student student = null;
		String sqlFindStudentById = "SELECT student_id, name, email, comments "
									+ "FROM student "
									+ "WHERE student_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindStudentById, studentId);
		if (results.next()) {
			student = mapRowToStudent(results);
		}
		return student;
	}

	@Override
	public Student editStudent(Student student) {	
		String sqlEditStudent = "UPDATE student SET name = ?, email = ?, comments = ? WHERE student_id = ?";
		jdbcTemplate.update(sqlEditStudent, student.getName(), student.getEmail(), student.getComment(), student.getStudentId());
		
		return student;
	}

	@Override
	public List<Student> getStudentsbyClassId(int classId) {
		List<Student> studentsByClass = new ArrayList<>();
		String sqlStudentsByClass = "SELECT student_id, name, email, comments FROM student "
									+ "JOIN instructor_student ON instructor_student.student_id = student.student_id "
									+ "JOIN instructor ON instructor.instructor_id = instructor_student.instructor_id "
									+ "JOIN instructor_class ON instructor_class.instructor_id = instructor.instructor_id "
									+ "JOIN class ON class.class_id = instructor_class.class_id "
									+ "WHERE class.class_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStudentsByClass, classId);
		if(results.next()) {
			Student s = new Student();
			studentsByClass.add(s);
		}
		
		return studentsByClass;
	}
	
	private Student mapRowToStudent(SqlRowSet results) {
		Student student = new Student();
		student.setStudentId(results.getInt("student_id"));
		student.setName(results.getString("name"));
		student.setEmail(results.getString("email"));
		student.setComment(results.getString("comments"));
		return student;
	}
}
