package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCClassDAO implements ClassDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCClassDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addClass(Class newClass) {
		String sqlToAddClass = "INSERT INTO class (id,name) VALUES (default, ?)";
		jdbcTemplate.update(sqlToAddClass, newClass.getName());	
	}

	@Override
	public List<Class> viewClasses(int instructorId) {
		List<Class> classList =  new ArrayList<>();
		String sqlSelectAllClasses = "SELECT * FROM class "
									+ "JOIN instructor_class ON instructor_class.class_id"
									+ "= class.class_id "
									+ "JOIN instructor ON instructor.instructor_id = instructor_class.instructor_id"
									+ "WHERE instructor_id = ? ORDER BY ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllClasses, instructorId);
		while(results.next()) {
			Class c = mapRowToClass(results);
			classList.add(c);
		}
		return classList;
	}

	private Class mapRowToClass(SqlRowSet results) {
		
		Class c = new Class();
		c.setClassId(results.getInt("class_id"));
		c.setName(results.getString("name"));
		
		return c;
	}
	
}
