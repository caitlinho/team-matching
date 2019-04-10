package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Instructor;
import com.techelevator.model.JDBCInstructorDAO;

public class JDBCInstructorDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCInstructorDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setupTest() {
		dao = new JDBCInstructorDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void get_instructor_by_id_returns_correct_id_from_user() {
		//Arrange
		Instructor instructor = getExampleInstructor(1, "Brian Lauvray");

		//Act
		int actual = dao.getInstructorById(user);
		
		//Assert
		assertEquals(1, actual);
	}
	
	private Instructor getExampleInstructor(int instructorId,  String name) {
		Instructor theInstructor = new Instructor();
		theInstructor.setId(instructorId);
		theInstructor.setName(name);
		return theInstructor;
	}
}
