package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCInstructorDAO implements InstructorDAO{
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCInstructorDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int getInstructorById(String userName) {
		int instructorId = 0;
		String sqlGetInstructorById = "SELECT instructor.instructor_id FROM instructor "
									+ "JOIN app_user_instructor ON app_user_instructor.instructor_id "
									+ "= instructor.instructor_id "
									+ "JOIN app_user ON app_user.id = app_user_instructor.id "
									+ "WHERE app_user.user_name = ? ";

		 SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetInstructorById, userName);
		
		 if(results.next()) {
			 instructorId = mapRowToInstructor(results).getId();
		 }
		 
		 return instructorId;
	}
	
	private Instructor mapRowToInstructor(SqlRowSet results) {
		Instructor instructor = new Instructor();
		instructor.setId(results.getInt("instructor_id"));
		instructor.setName(results.getString("name"));
		return instructor;
	}
		
}



