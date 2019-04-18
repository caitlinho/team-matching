package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.techelevator.model.InstructorClasses;
import com.techelevator.model.Matches;
import com.techelevator.model.Student;
import com.techelevator.model.User;
import com.techelevator.model.jdbc.JDBCInstructorClassesDAO;
import com.techelevator.model.jdbc.JDBCMatchesDAO;
import com.techelevator.model.jdbc.JDBCStudentDAO;
import com.techelevator.model.jdbc.JDBCUserDAO;
import com.techelevator.security.PasswordHasher;

public class JDBCMatchesDAOIntegrationTest extends DAOIntegrationTest {
	
	private JDBCMatchesDAO dao;
	private JdbcTemplate jdbcTemplate;
	private JDBCStudentDAO studentDAO;
	private JDBCInstructorClassesDAO instructorDAO;
	private JDBCUserDAO userDAO;
	private JDBCMatchesDAO matchDAO;
	
	@Before
	public void setupTest() {
		dao = new JDBCMatchesDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		userDAO = new JDBCUserDAO(getDataSource(), new PasswordHasher());
		studentDAO = new JDBCStudentDAO(getDataSource());
		matchDAO = new JDBCMatchesDAO(getDataSource());
	}
	
	@Test
	public void compare_matches_generates_and_saves_correct_match() {
		//Arrange
//		InstructorClasses theClass0 = getNewClass(1, "JavaBlue");
// 		InstructorClasses theClass1 = getNewClass(2, "JavaGreen");
// 		
// 		User instructor = getExampleUser(1, "Brian Lauvray", "!CodingRules1", "instructor");
//		User secondInstructor = getExampleUser(2, "Steve Carmichael", "!Abc23", "instructor");
//		
//		Matches matchOne = getExampleMatch
		//Arrange
		Student studentOne = getExampleStudent(1, "Jenny", "jenny@email.com", "awesome");
 		Student studentTwo =  getExampleStudent(2, "Bob", "bob@email.com", "mediocre");
 		Student studentThree = getExampleStudent(3, "Bill", "bill@email.com", "awful"); 
 		
 		Matches match = mapRowToMatch(1, 3, 1, 2, 3, 2, 3);
 		
 		
 		Student studentFour = getExampleStudent(4, "Katie", "katie@email.com", "cool");
 		Student studentFive =  getExampleStudent(5, "Steve", "steve@email.com", "boring");
 		Student studentSix = getExampleStudent(6, "Jim", "jiml@email.com", "chill"); 
 		
 		Matches matchTwo = mapRowToMatch(2, 3, 4, 5, 6, 2, 3);
 		
 		
 		//Act
// 		 boolean matchOneAnswer = matchDAO.compareMatches(match);
 		 assertTrue(matchDAO.compareMatches(match));
 		 assertTrue(matchDAO.compareMatches(match));
 		 assertTrue(matchDAO.compareMatches(match));
 		 assertFalse(matchDAO.compareMatches(match));
 		

	}
	
	private InstructorClasses getNewClass(int classId, String name) {
 		InstructorClasses theClass = new InstructorClasses();
 		theClass.setClassId(classId);
 		theClass.setName(name);
 		return theClass;
 	}
	
	
	
	private User getExampleUser(int id,  String userName, String password, String role) {
 		User theInstructor = new User();
 		userDAO.saveUser(userName, password, role);
 		theInstructor.setId(id);
 		theInstructor.setUserName(userName);
		theInstructor.setPassword(password);
		theInstructor.setRole(role);
		
 		return theInstructor;
 	}
	private Student getExampleStudent(int studentId, String name, String email, String comment) {
		Student student = new Student();
		student.setStudentId(studentId);
		student.setName(name);
		student.setEmail(email);
		student.setComment(comment);
		
		return student;
		
	}
	
	
	private Matches mapRowToMatch(int matchId, int size, int studentId1, int studentId2, int studentId3, int countOfMatches, int week) {
		Matches match = new Matches();
		match.setMatchId(matchId);
		match.setSize(size);
		match.setStudentId1(studentId1);
		match.setStudentId2(studentId2);
		match.setStudentId3(studentId3);
		match.setCount(countOfMatches);
		match.setWeek(week);
		return match;
	}
	
	
	private void insertExampleInstructorIntoDatabase(User instructor) {
		String sql = "INSERT INTO app_user (id, user_name, password, role, salt) " 
					+ "VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, instructor.getId(), instructor.getUserName(), instructor.getPassword(), instructor.getRole(), "salt");
	}
	
	private void insertIntoInstructorClassJoinTable(int instructorId, int classId) {
		String sql = "INSERT INTO app_user_class (id, class_id) "
					+ "VALUES (?, ?)";
		jdbcTemplate.update(sql, instructorId, classId);
	}
	
	
	
	private void clearMatchesTable() {
		String truncateMatchTableSql = "DELETE FROM matches";
		jdbcTemplate.update(truncateMatchTableSql);
	}

}
