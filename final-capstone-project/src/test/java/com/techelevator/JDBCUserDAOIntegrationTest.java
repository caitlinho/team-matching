package com.techelevator;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;


import com.techelevator.model.JDBCUserDAO;

public class JDBCUserDAOIntegrationTest extends DAOIntegrationTest {
	
	private JdbcTemplate jdbcTemplate;
	private JDBCUserDAO dao;
	
	@Before
	public void setupTest() {
		dao = new JDBCUserDAO(getDataSource(), null);
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}

}
