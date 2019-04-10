package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



@Component
public class JDBCStudentsDAO implements StudentsDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCStudentsDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addStudentList() {
	String sqlToAddStudentsList = "COPY student (student_id, name, email, comments) FROM '/Users/gsutter/Development/capstone/final-capstone-team-bravo/studentList.csv' DELIMITERS ',' CSV header FORCE QUOTE *;\n"; 
	jdbcTemplate.update(sqlToAddStudentsList);
	 
			
	}

	@Override
	public Students editStudent(Students student) {	
		String sqlEditStudent = "UPDATE student SET name = ?, email = ?, comments = ? WHERE student_id = ?";
		jdbcTemplate.update(sqlEditStudent, student.getStudentId());
		
		return student;
	}

}
