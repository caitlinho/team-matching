package com.techelevator;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBCStudentsDAO;

public class JDBCStudentsDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCStudentsDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setupTest() {
//		dao = new JDBCStudentsDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
}
