package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.techelevator.model.Matches;
import com.techelevator.model.MatchesDAO;
import com.techelevator.model.Student;

public class JDBCMatchesDAO implements MatchesDAO{
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCMatchesDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public List<Matches> getMatchesbyUsername(String username) {
		List<Matches> matchesByClass = new ArrayList<>();
		String sqlMatchesByClass = "SELECT matches.match_id, student.name, student.name, student.name, size, week, count_of_matches FROM matches"  
								 + "JOIN student ON student.student_id = matches.student_id_1 " 
								 + "JOIN class_student ON class_student.student_id = student.student_id "
								 + "JOIN class ON class.class_id = class_student.class_id " 
								 + "JOIN app_user_class ON app_user_class.class_id = class.class_id "
								 + "JOIN app_user ON app_user.id = app_user_class.id"
								 + "WHERE app_user.user_name = ? ORDER BY class.name DESC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlMatchesByClass, username);
		
		while(results.next()) {
			matchesByClass.add(mapRowToMatch(results));
		}
		return matchesByClass;
	}
	

	
	@Override
	public List<Matches> viewMatches(Matches match) {
		List<Matches> matches = new ArrayList<>();
		String sqlMatches = "SELECT student_id_1, student_id_2, student_id_3, week, count_of_matches FROM matches WHERE week = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlMatches, match.getWeek());
		
		while(results.next()) {
			matches.add(mapRowToMatch(results));
		}
		return matches;
	}
	
	@Override
	public List<Matches> getMatchesbyStudentId(int studentId) {
		List<Matches> matchesByStudent = new ArrayList<>();
		String sqlMatches = "SELECT matches.match_id, student.name, student.name, student.name, size, week, count_of_matches FROM matches "  
						  + "JOIN student ON student.student_id = matches.student_id_1 "  
						  + "WHERE student_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlMatches, studentId);
		
		while(results.next()) {
			matchesByStudent.add(mapRowToMatch(results));
		}
		return matchesByStudent;
	}
	
	@Override
	public void compareMatches(Matches match) {
		if (match.getSize() == 2) {
			String sql = "SELECT * FROM matches WHERE (student_id_1 = ? AND student_id_2 = ?) || (student_id_1 = ? AND student_id_2 = ?)";
			Matches sameMatch = jdbcTemplate.queryForObject(sql, Matches.class, match.getStudentId1(), match.getStudentId2(), match.getStudentId2(), match.getStudentId1());
			if (sameMatch != null) {
				updateCountForPairs(match.getStudentId1(), match.getStudentId2());
			}
			else {
				saveMatchesForPairs(match);
				insertIntoMatchStudentJoinTable(match.getMatchId(), match.getStudentId1());
				insertIntoMatchStudentJoinTable(match.getMatchId(), match.getStudentId2());	
			}
		} else {
			String sql = "SELECT * FROM matches WHERE (student_id_1 = ? AND student_id_2 = ? AND student_id_3 = ?) || (student_id_1 = ? AND student_id_2 = ? student_id_3 = ?) || "
												   + "(student_id_1 = ? AND student_id_2 = ? AND student_id_3 = ?) || (student_id_1 = ? AND student_id_2 = ? student_id_3 = ?) || "
												   + "(student_id_1 = ? AND student_id_2 = ? AND student_id_3 = ?) || (student_id_1 = ? AND student_id_2 = ? student_id_3 = ?)";
			Matches sameMatch = jdbcTemplate.queryForObject(sql, Matches.class, 
												match.getStudentId1(), match.getStudentId2(), match.getStudentId3(), match.getStudentId2(), match.getStudentId3(), match.getStudentId1(),
												match.getStudentId1(), match.getStudentId3(), match.getStudentId2(), match.getStudentId2(), match.getStudentId1(), match.getStudentId3(),
												match.getStudentId3(), match.getStudentId1(), match.getStudentId2(), match.getStudentId3(), match.getStudentId2(), match.getStudentId1());
			if (sameMatch != null) {
				updateCountForTriples(match.getStudentId1(), match.getStudentId2(), match.getStudentId3());
			}
			else {
				saveMatchesForTriples(match);
				insertIntoMatchStudentJoinTable(match.getMatchId(), match.getStudentId1());
				insertIntoMatchStudentJoinTable(match.getMatchId(), match.getStudentId2());	
				insertIntoMatchStudentJoinTable(match.getMatchId(), match.getStudentId3());
			}
		}
		
	}
	
	private Matches mapRowToMatch(SqlRowSet results) {
		Matches match = new Matches();
		match.setMatchId(results.getInt("match_id"));
		match.setSize(results.getInt("size"));
		match.setStudentId1(results.getInt("student_id_1"));
		match.setStudentId2(results.getInt("student_id_2"));
		match.setStudentId3(results.getInt("student_id_3"));
		match.setCount(results.getInt("count_of_matches"));
		match.setWeek(results.getInt("week"));
		return match;
	}	
	
	private void insertIntoMatchStudentJoinTable(int matchId, int studentId) {
		String sql = "INSERT INTO match_student (match_id, student_id) "
					+ "VALUES (?, ?)";
		jdbcTemplate.update(sql, matchId, studentId);
	}
	
	private void saveMatchesForPairs(Matches matches) {
		String sqlAddMatches = "INSERT INTO matches (match_id, student_id_1, student_id_2, week, size, count) "
							 + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING match_id";
		int matchId = jdbcTemplate.queryForObject(sqlAddMatches, Integer.class, matches.getStudentId1(), 
																				matches.getStudentId2(),
																				matches.getWeek(),
																				matches.getSize(),
																				matches.getCount());
	}
	
	private void saveMatchesForTriples(Matches matches) {
		String sqlAddMatches = "INSERT INTO matches (match_id, student_id_1, student_id_2, student_id_3, week, size, count) "
							 + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING match_id";
		int matchId = jdbcTemplate.queryForObject(sqlAddMatches, Integer.class, matches.getStudentId1(), 
																				matches.getStudentId2(),
																				matches.getStudentId3(),
																				matches.getWeek(),
																				matches.getSize(),
																				matches.getCount());
	}
	
	private void updateCountForPairs(int studentId1, int studentId2) {
		String sql = "UPDATE matches SET count_of_matches = count_of_matches + 1 WHERE (student_id_1 = ? AND student_id_2 = ?) || (student_id_1 = ? AND student_id_2 = ?";
		jdbcTemplate.update(sql, studentId1, studentId2, studentId2, studentId1);
	}
	
	private void updateCountForTriples(int studentId1, int studentId2, int studentId3) {
		String sql = "UPDATE matches SET count_of_matches = count_of_matches + 1 WHERE student_id_1 = ? AND student_id_2 = ? AND student_id_3";
		jdbcTemplate.update(sql, studentId1, studentId2, studentId3);
	}
}
