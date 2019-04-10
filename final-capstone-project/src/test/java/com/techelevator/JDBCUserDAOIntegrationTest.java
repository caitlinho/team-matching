package com.techelevator;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBCInstructorClassesDAO;
import com.techelevator.model.JDBCUserDAO;

public class JDBCUserDAOIntegrationTest extends  {
	
	private JdbcTemplate jdbctemplate;
	private JDBCUserDAO dao;
	
	@Before
	public void setupTest() {
		dao = new JDBCUserDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}

}
