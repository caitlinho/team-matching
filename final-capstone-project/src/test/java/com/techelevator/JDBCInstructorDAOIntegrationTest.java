package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Instructor;
import com.techelevator.model.JDBCInstructorDAO;
import com.techelevator.model.User;

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
		User user = getExampleUser(1, "instructor", "blauvray", "!CodingRules1", "!CodingRules1");
		
		//Act
		insertExampleInstructorIntoDatabase(instructor);
		insertUserIntoDatabase(user);
		int actual = dao.getInstructorById(user.getUserName());
		
		//Assert
		assertEquals(1, actual);
	}
	
	@Test
	public void get_instructor_by_id_returns_only_one_id() {
		//Arrange
		Instructor instructor = getExampleInstructor(1, "Brian Lauvray");
		User user = getExampleUser(1, "instructor", "blauvray", "!CodingRules1", "!CodingRules1");

		// Act
		insertExampleInstructorIntoDatabase(instructor);
		insertUserIntoDatabase(user);
		insertJoinTable(user.getId(), instructor.getId());
		dao.getInstructorById(user.getUserName());
		int newSize = jdbcTemplate.queryForObject("SELECT COUNT(instructor.instructor_id) FROM class", Integer.class);
		
		//Assert
		assertEquals(1, newSize);
	}
	
	private Instructor getExampleInstructor(int instructorId,  String name) {
		Instructor theInstructor = new Instructor();
		theInstructor.setId(instructorId);
		theInstructor.setName(name);
		return theInstructor;
	}
	
	private User getExampleUser(int id, String role, String userName, String password, String confirmPassword) {
		User theUser = new User();
		theUser.setId(id);
		theUser.setRole(role);
		theUser.setUserName(userName);
		theUser.setPassword(password);
		theUser.setConfirmPassword(confirmPassword);
		return theUser;
	}
	
	private void insertExampleInstructorIntoDatabase(Instructor instructor) {
		String sql = "INSERT INTO instructor (instructor_id, name) " 
					+ "VALUES (?,?)";
		jdbcTemplate.update(sql, instructor.getId(), instructor.getName());
	}
	
	private void insertUserIntoDatabase(User user) {
		String sql = "Insert INTO app_user (id, user_name, password, role) VALUES (DEFAULT, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getRole());
	}
	
	private void insertJoinTable(int id, int instructorId) {
		String sql = "Insert INTO app_user_instructor (id, instructor_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, id, instructorId);
	}
}
