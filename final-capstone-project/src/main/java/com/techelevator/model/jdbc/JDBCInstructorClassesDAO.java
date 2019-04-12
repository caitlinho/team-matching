package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.InstructorClasses;
import com.techelevator.model.InstructorClassesDAO;

@Component
public class JDBCInstructorClassesDAO implements InstructorClassesDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCInstructorClassesDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public void addClass(InstructorClasses newClass) {
		String sqlToAddClass = "INSERT INTO class (class_id,name) VALUES (default, ?) RETURNING class_id";
		int classId = jdbcTemplate.queryForObject(sqlToAddClass, Integer.class, newClass.getName());
		newClass.setClassId(classId);
	}

	@Override
	public List<InstructorClasses> viewClasses(int instructorId) {
		List<InstructorClasses> classList =  new ArrayList<>();
		String sqlSelectAllClasses = "SELECT class.class_id, class.name FROM class "
									+ "JOIN instructor_class ON instructor_class.class_id"
									+ "= class.class_id "
									+ "JOIN instructor ON instructor.instructor_id = instructor_class.instructor_id "
									+ "WHERE instructor_class.instructor_id = ? ORDER BY class.name ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllClasses, instructorId);
		while(results.next()) {
			InstructorClasses c = mapRowToClass(results);
			classList.add(c);
		}
		return classList;
	}

	private InstructorClasses mapRowToClass(SqlRowSet results) {
		
		InstructorClasses c = new InstructorClasses();
		c.setClassId(results.getInt("class_id"));
		c.setName(results.getString("name"));
		
		return c;
	}
	
	
	
}
