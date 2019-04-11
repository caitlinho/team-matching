package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JDBCStudentDAO;
import com.techelevator.model.Student;

public class JDBCStudentDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCStudentDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setupTest() {
		dao = new JDBCStudentDAO(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
	}

	@Test
	public void add_student_list_adds_correct_amount_of_students() {
		// Arrange/Act
		dao.addStudentList();
		int size = jdbcTemplate.queryForObject("SELECT COUNT(name) FROM student", Integer.class);

		// Assert
		assertEquals(3, size);
	}

	@Test
	public void get_student_by_id_returns_one_student() {
		// Arrange
		Student student = getExampleStudent(1, "Dominick Hemphill", "dhemp@gmail.com", "smiles on smiles.");
		insertExampleStudentIntoDatabase(student);

		// Act
		dao.getStudentById(student.getStudentId());
		int numberOfStudentsReturned = jdbcTemplate.queryForObject("SELECT COUNT(name) FROM student", Integer.class);

		// Assert
		assertEquals(1, numberOfStudentsReturned);
	}

	@Test
	public void get_student_by_id_returns_the_correct_student() {
		// Arrange
		Student student = getExampleStudent(1, "Dominick Hemphill", "dhemp@gmail.com", "smiles on smiles.");
		insertExampleStudentIntoDatabase(student);

		// Act
		Student actual = dao.getStudentById(student.getStudentId());
		
		//Assert
		assertEquals(student.getStudentId(), actual.getStudentId());
		assertEquals(student.getName(), actual.getName());
		assertEquals(student.getEmail(), actual.getEmail());
		assertEquals(student.getComment(), actual.getComment());
	}

	private Student getExampleStudent(int studentId, String name, String email, String comments) {
		Student theStudent = new Student();
		theStudent.setStudentId(studentId);
		theStudent.setName(name);
		theStudent.setEmail(email);
		theStudent.setComment(comments);
		return theStudent;
	}
	
	private void insertExampleStudentIntoDatabase(Student student) {
		String sql = "INSERT INTO student(student_id, name, email, comments) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, student.getStudentId(), student.getName(), student.getEmail(), student.getComment());
	}
}
