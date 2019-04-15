package com.techelevator.model.jdbc;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.RandomGeneratorDAO;

public class JDBCRandomGeneratorDAO implements RandomGeneratorDAO{

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void JDBCRandomGeneratorDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Array> listWeekMatches(int weekId) {
		List<Array> weekMatches = new ArrayList<>();
		String matchesSql = "SELECT pair FROM matches WHERE week_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(matchesSql, weekId);
		return null;
	}

	@Override
	public void saveMatches(List<Array> matches) {
		
	}
	
	public void addOneToCount(List<Array> totalCombinations, List<Array> matches, int countParam) {
		deleteIfParamIsMet(countParam);
		for(int i =0; i < matches.size(); i++) {
			for(int j = 0; j < totalCombinations.size(); j++) {
				if(totalCombinations.contains(i)) {
					
				}
			}
		}
	}

	@Override
	public List<Array> totalCombinations(int classId, int groupSize) {
		
		return null;
	}
	
	private void deleteIfParamIsMet(int countParam) {
		int matchCount = getCountOfPairs();
		String deleteSQL = "DELETE FROM total_table WHERE match_count = ?";
		if(matchCount+1 == countParam) {
			jdbcTemplate.update(deleteSQL, countParam);
		}
	}
	
	private int getCountOfPairs() {
		String countSQL = "SELECT match_count FROM total_table";
		int results = jdbcTemplate.queryForObject(countSQL, Integer.class);
		return results;
	}

}
