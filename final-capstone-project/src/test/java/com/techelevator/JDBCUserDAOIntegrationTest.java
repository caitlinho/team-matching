package com.techelevator;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;


import com.techelevator.model.JDBCUserDAO;

<<<<<<< HEAD
public class JDBCUserDAOIntegrationTest extends DAOIntegrationTest {
=======
public class JDBCUserDAOIntegrationTest   {
>>>>>>> 6c2a57a9ec5fd64366411723dc5392c80c8e1d35
	
	private JdbcTemplate jdbcTemplate;
	private JDBCUserDAO dao;
	
	@Before
	public void setupTest() {
		dao = new JDBCUserDAO(getDataSource(), null);
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}

}
