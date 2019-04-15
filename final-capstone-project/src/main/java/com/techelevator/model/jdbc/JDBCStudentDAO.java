package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Student;
import com.techelevator.model.StudentDAO;


@Component
public class JDBCStudentDAO implements StudentDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCStudentDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void addStudentList(List<Student> studentList) {
		String sqlToAddStudent = "INSERT INTO student (student_id, name, email, comments) VALUES (DEFAULT, ?, ?, ?) "
								+ "RETURNING student_id;";
		for (int index = 0; index < studentList.size(); index++) {
		int studentId = jdbcTemplate.queryForObject(sqlToAddStudent, Integer.class, studentList.get(index).getName(), 
																					studentList.get(index).getEmail(), 
																					studentList.get(index).getComment());
		studentList.get(index).setStudentId(studentId);
		}
	}

	@Override
	public Student getStudentById(int studentId) {
		Student student = null;
		String sqlFindStudentById = "SELECT student_id, name, email, comments "
									+ "FROM student "
									+ "WHERE student_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindStudentById, studentId);
		if (results.next()) {
			student = mapRowToStudent(results);
		}
		return student;
	}

	@Override
	public Student editStudent(Student student) {	
		String sqlEditStudent = "UPDATE student SET name = ?, email = ?, comments = ? WHERE student_id = ?";
		jdbcTemplate.update(sqlEditStudent, student.getName(), student.getEmail(), student.getComment(), student.getStudentId());
		
		return student;
	}
	
	@Override
	public void addStudent(Student student, int classId) {	
		String sqlAddStudent = "INSERT INTO student (student_id, name, email, comments) VALUES (DEFAULT, ?, ?, ?) RETURNING student_id";
		int studentId = jdbcTemplate.queryForObject(sqlAddStudent, Integer.class, student.getName(), student.getEmail(), student.getComment());
		student.setStudentId(studentId);
	
		insertIntoStudentClassJoinTable(classId, studentId);
	}
	
	@Override
	public void deleteStudent(int studentId, int classId) {
		String sqlDelete = "DELETE FROM student WHERE student_id = ?;";
		jdbcTemplate.queryForRowSet(sqlDelete, studentId);
		deleteStudentClassJoinTable(studentId);
	}

	@Override
	public List<Student> getStudentsbyClassId(int classId) {
		List<Student> studentsByClass = new ArrayList<>();
		String sqlStudentsByClass = "SELECT student.student_id, student.name, email, comments FROM student "
									+ "JOIN class_student ON class_student.student_id = student.student_id "
									+ "JOIN class ON class.class_id = class_student.class_id "
									+ "WHERE class.class_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStudentsByClass, classId);
		if(results.next()) {
			Student s = new Student();
			studentsByClass.add(s);
		}
		
		return studentsByClass;
	}
	
	private Student mapRowToStudent(SqlRowSet results) {
		Student student = new Student();
		student.setStudentId(results.getInt("student_id"));
		student.setName(results.getString("name"));
		student.setEmail(results.getString("email"));
		student.setComment(results.getString("comments"));
		return student;
	}
	
	private void insertIntoStudentClassJoinTable(int classId, int studentId) {
		String sql = "INSERT INTO class_student (class_id, student_id) "
					+ "VALUES (?, ?)";
		jdbcTemplate.update(sql, classId, studentId);
	}
	
	private void deleteStudentClassJoinTable(int studentId) {
		String sql = "DELETE FROM class_student WHERE student_id = ? ";
		jdbcTemplate.update(sql, studentId);
	}
}