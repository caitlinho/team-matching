package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.InstructorClasses;
import com.techelevator.model.User;
import com.techelevator.model.jdbc.JDBCInstructorClassesDAO;
import com.techelevator.model.jdbc.JDBCUserDAO;
import com.techelevator.security.PasswordHasher;

public class JDBCInstructorClassesDAOIntegrationTest extends DAOIntegrationTest {
 	
 	private JDBCInstructorClassesDAO dao;
 	private JDBCUserDAO userDAO;
 	private JdbcTemplate jdbcTemplate;
 	
	 	@Before
	 	public void setupTest() {
	 		dao = new JDBCInstructorClassesDAO(getDataSource());
	 		jdbcTemplate = new JdbcTemplate(getDataSource());
	 		
			userDAO = new JDBCUserDAO(getDataSource(), new PasswordHasher());
	 	}
 	
 		@Test
 		public void new_classes_are_inserted_into_database() {
 		//Arrange
 		InstructorClasses theClass0 = getNewClass(1, "JavaBlue");
 		InstructorClasses theClass1 = getNewClass(2, "JavaGreen");
 		User instructor = getExampleUser(1, "Brian Lauvray", "!CodingRules1", "instructor");
 		
		
 		
 		//Act
// 		userDAO.saveUser(instructor.getUserName(), instructor.getPassword(), instructor.getRole());
 		dao.addClass(theClass0, instructor.getId());
 		dao.addClass(theClass1, instructor.getId());
		insertExampleInstructorIntoDatabase(instructor);
		insertIntoInstructorClassJoinTable(instructor.getId(), theClass0.getClassId());
		insertIntoInstructorClassJoinTable(instructor.getId(), theClass1.getClassId());
 		List<InstructorClasses> classList = dao.viewClasses(instructor.getId());
 		
 		//Assert
 		assertEquals(2, classList.size());
 	}
 	
	@Test
	public void view_classes_returns_only_that_instructors_classes() {
		//Arrange
		InstructorClasses theClass0 = getNewClass(1, "JavaBlue");
		InstructorClasses theClass1 = getNewClass(2, "JavaGreen");
		
		User instructor = getExampleUser(1, "Brian Lauvray", "!CodingRules1", "instructor");
		User secondInstructor = getExampleUser(2, "Steve Carmichael", "!AbC23", "instructor");
		
		//Act
//		userDAO.saveUser(instructor.getUserName(), instructor.getPassword(), instructor.getRole());
//		userDAO.saveUser(secondInstructor.getUserName(), secondInstructor.getPassword(), secondInstructor.getRole());
		dao.addClass(theClass0, instructor.getId());
		dao.addClass(theClass1, secondInstructor.getId());
		insertExampleInstructorIntoDatabase(instructor);
		insertIntoInstructorClassJoinTable(instructor.getId(), theClass0.getClassId());
		insertIntoInstructorClassJoinTable(secondInstructor.getId(), theClass1.getClassId());
		List<InstructorClasses> classList = dao.viewClasses(instructor.getId());
		
		//Assert
		assertEquals(1, classList.size());
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
	
	private void clearClassTable() {
		String truncateClassTableSql = "DELETE FROM class";
		jdbcTemplate.update(truncateClassTableSql);
	}
 

}
