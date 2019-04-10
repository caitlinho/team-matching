package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBCStudentsDAO;
import com.techelevator.model.Students;

public class JDBCStudentsDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCStudentsDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setupTest() {
		dao = new JDBCStudentsDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void add_student_list_adds_correct_amount_of_students() {
		//Arrange/Act
		dao.addStudentList();
		int size = jdbcTemplate.queryForObject("SELECT COUNT(name) FROM student", Integer.class); 
		
		//Assert
		assertEquals(3, size);
	}
}
