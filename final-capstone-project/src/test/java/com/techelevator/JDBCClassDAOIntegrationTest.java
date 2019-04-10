package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.InstructorClasses;
import com.techelevator.model.JDBCInstructorClassesDAO;

public class JDBCClassDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCInstructorClassesDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setupTest() {
		dao = new JDBCInstructorClassesDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void new_classes_are_inserted_into_database() {
		//Arrange
		InstructorClasses theClass = getNewClass(1, "Java Blue");
		
		
		//Act
		dao.addClass(theClass);
		int newSize = jdbcTemplate.queryForObject("SELECT COUNT(name) FROM class", Integer.class);
		
		//Assert
		assertEquals(1, newSize);
	}
	
	

	private InstructorClasses getNewClass(int classId, String name) {
		InstructorClasses theClass = new InstructorClasses();
		theClass.setClassId(classId);
		theClass.setName(name);
		return theClass;
	}
	
	@Test
	public void get_classes_returns_correct_number_of_classes() {
		//
		
	}
	
	
}
