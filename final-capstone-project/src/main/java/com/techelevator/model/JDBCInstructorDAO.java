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
	public String getInstructorById(String userName) {
		String instructorId = new String();
		String sqlGetInstructorById = "SELECT instructor_id FROM instructor "
				+ "						JOIN app_user_instructor ON app_user_instructor.instructor_id"
				+ "						= instructor.instuctor_id	"
				+ "						JOIN app_user ON app_user.id = app_user_instructor.id"
				+ "						WHERE app_user.user_name = ? ";

		 jdbcTemplate.queryForRowSet(sqlGetInstructorById, userName);
		
		 return instructorId;
	}
		
}



