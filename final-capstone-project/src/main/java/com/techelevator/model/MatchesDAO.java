package com.techelevator.model;

import java.util.List;

public interface MatchesDAO {
	
	List<Matches> getMatchesbyUsername(String username);
	List<Matches> getMatchesbyStudentId(int studentId);
	List<Matches> viewMatches(Matches match);
	boolean compareMatches(Matches match);
	void saveMatchesForPairs(Matches matches);
	void saveMatchesForTriples(Matches matches);
	void insertParam(ClassParam param);
	void updateParam(ClassParam param);
	ClassParam getParam();

}
