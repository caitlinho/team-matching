package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.ClassParam;
import com.techelevator.model.Matches;
import com.techelevator.model.MatchesDAO;

@Component
public class JDBCMatchesDAO implements MatchesDAO{
	
private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	public JDBCMatchesDAO (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Matches> getMatchesbyUsername(String username) {
		List<Matches> matchesByClass = new ArrayList<>();
		String sqlMatchesByClass = "SELECT s1.name, s2.name, match_ex.week, c.name, match_ex.count_of_matches "
								+ "FROM match_ex "
								+ "JOIN class_parameters param ON param.count_limit = match_ex.count_limit "
								+ "JOIN class_parameters param2 ON param2.week = match_ex.week "
								+ "JOIN class_parameters param3 ON param3.size = match_ex.size "
								+ "JOIN student s1 ON s1.student_id = match_ex.student_id_1 "
								+ "JOIN student s2 ON s2.student_id = match_ex.student_id_2 "
								+ "JOIN class_student cs ON s1.student_id = cs.student_id "
								+ "JOIN class c ON c.class_id = cs.class_id "
								+ "JOIN app_user_class auc ON auc.class_id = c.class_id "
								+ "JOIN app_user au ON au.id = auc.id "
								+ "WHERE au.id = 1 "
								+ "ORDER BY c.name ASC";

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
		String sqlMatches = "SELECT s1.name, s2.name, m.week, m.count_of_matches FROM matches m "  
						  + "JOIN student s1 ON m.student_id_1 = s1.student_id "  
						  + "JOIN student s2 ON m.student_id_2 = s2.student_id "  
						  + "WHERE student_id_1 = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlMatches, studentId);
		
		while(results.next()) {
			matchesByStudent.add(mapRowToMatch(results));
		}
		return matchesByStudent;
	}
	
	@Override
	public boolean compareMatches(Matches match) {
		boolean matchBoolean = true;
		if (match.getSize() == 2) {
			String sql = "SELECT count_of_matches FROM matches WHERE (student_id_1 = ? AND student_id_2 = ?) OR (student_id_1 = ? AND student_id_2 = ?)";
			SqlRowSet sameMatch = jdbcTemplate.queryForRowSet(sql, match.getStudentId1(), match.getStudentId2(), match.getStudentId2(), match.getStudentId1());
			boolean hasResults = sameMatch.next();
			if (hasResults && sameMatch.getInt("count_of_matches") != 0) {
				if (countOfMatchesForPairs(match.getStudentId1(), match.getStudentId2()) < match.getCount()) {
					updateCountForPairs(match.getStudentId1(), match.getStudentId2());
					matchBoolean = true;
				}
				else {
					
					matchBoolean = false;
				}
			}
		} else {
			String sql = "SELECT * FROM matches WHERE (student_id_1 = ? AND student_id_2 = ? AND student_id_3 = ?) OR (student_id_1 = ? AND student_id_2 = ? student_id_3 = ?) OR "
												   + "(student_id_1 = ? AND student_id_2 = ? AND student_id_3 = ?) OR (student_id_1 = ? AND student_id_2 = ? student_id_3 = ?) OR "
												   + "(student_id_1 = ? AND student_id_2 = ? AND student_id_3 = ?) OR (student_id_1 = ? AND student_id_2 = ? student_id_3 = ?)";
			Matches sameMatch = jdbcTemplate.queryForObject(sql, Matches.class, 
												match.getStudentId1(), match.getStudentId2(), match.getStudentId3(), match.getStudentId2(), match.getStudentId3(), match.getStudentId1(),
												match.getStudentId1(), match.getStudentId3(), match.getStudentId2(), match.getStudentId2(), match.getStudentId1(), match.getStudentId3(),
												match.getStudentId3(), match.getStudentId1(), match.getStudentId2(), match.getStudentId3(), match.getStudentId2(), match.getStudentId1());
			if (sameMatch != null) {
				if (countOfMatchesForTriples(match.getStudentId1(), match.getStudentId2(), match.getStudentId3()) < match.getCount()) {
				
					updateCountForTriples(match.getStudentId1(), match.getStudentId2(), match.getStudentId3());
					matchBoolean = true;
				} else {
					matchBoolean = false;
				}
			}	
		}
		return matchBoolean;
	}
	
	@Override
	public void saveMatchesForPairs(Matches matches) {
		String sqlAddMatches = "INSERT INTO matches (match_id, student_id_1, student_id_2, week, size, count) "
							 + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING match_id";
		int matchId = jdbcTemplate.queryForObject(sqlAddMatches, Integer.class, matches.getStudentId1(), 
																				matches.getStudentId2(),
																				matches.getWeek(),
																				matches.getSize(),
																				matches.getCount());
		matches.setMatchId(matchId);
	}
	
	@Override
	public void saveMatchesForTriples(Matches matches) {
		String sqlAddMatches = "INSERT INTO matches (match_id, student_id_1, student_id_2, student_id_3, week, size, count_of_matches) "
							 + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING match_id";
		int matchId = jdbcTemplate.queryForObject(sqlAddMatches, Integer.class, matches.getStudentId1(), 
																				matches.getStudentId2(),
																				matches.getStudentId3(),
																				matches.getWeek(),
																				matches.getSize(),
																				matches.getCount());
		matches.setMatchId(matchId);
	}
	
	@Override
	public void insertParam(ClassParam param) {
		String sqlInsert = "INSERT INTO class_parameters (count_limit, size, week) VALUES (?, ?, ?) ";
		jdbcTemplate.queryForRowSet(sqlInsert, param.getCountLimit(), param.getSize(), param.getWeek());	
	}

	@Override
	public void updateParam(ClassParam param) {
		String updateSql = "UPDATE class_parameters SET count_limit = ?, size = ?, week = ? WHERE paramId = ?";
		jdbcTemplate.queryForRowSet(updateSql);
	}


	@Override
	public ClassParam getParam() {
		String sql = "SELECT * FROM class_parameters";
		ClassParam classParam = jdbcTemplate.queryForObject(sql, ClassParam.class);
			
		return classParam;
	}

	
	private Matches mapRowToMatch(SqlRowSet results) {
		Matches match = new Matches();
		match.setStudentId1(results.getInt("student_id_1"));
		match.setStudentId2(results.getInt("student_id_2"));
		match.setCount(results.getInt("count_of_matches"));
		match.setWeek(results.getInt("week"));
		return match;
	}	

	private int countOfMatchesForPairs(int studentId1, int studentId2) {
		String sql = "SELECT count_of_matches FROM matches WHERE (student_id_1 = ? AND student_id_2 = ?) OR (student_id_1 = ? AND student_id_2 = ?)";
		int results = jdbcTemplate.queryForObject(sql, Integer.class, studentId1, studentId2, studentId2, studentId1);
		return results;
	}
	
	private int countOfMatchesForTriples(int studentId1, int studentId2, int studentId3) {
		String sql = "SELECT count_of_matches FROM matches WHERE (student_id_1 = ? AND student_id_2 = ?) OR (student_id_1 = ? AND student_id_2 = ? AND student_id_3 = ?)";
		int results = jdbcTemplate.queryForObject(sql, Integer.class, studentId1, studentId2, studentId2, studentId1);
		return results;
	}
	
	private void updateCountForPairs(int studentId1, int studentId2) {
		String sql = "UPDATE matches SET count_of_matches = count_of_matches + 1 WHERE (student_id_1 = ? AND student_id_2 = ?) OR (student_id_1 = ? AND student_id_2 = ?)";
		jdbcTemplate.update(sql, studentId1, studentId2, studentId2, studentId1);
	}
	
	private void updateCountForTriples(int studentId1, int studentId2, int studentId3) {
		String sql = "UPDATE matches SET count_of_matches = count_of_matches + 1 WHERE student_id_1 = ? AND student_id_2 = ? AND student_id_3";
		jdbcTemplate.update(sql, studentId1, studentId2, studentId3);
	}
}
