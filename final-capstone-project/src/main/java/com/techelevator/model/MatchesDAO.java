package com.techelevator.model;

import java.util.List;

public interface MatchesDAO {
	
	List<Matches> getMatchesbyUsername(String username);
	List<Matches> getMatchesbyStudentId(int studentId);
	List<Matches> viewMatches(Matches match);
	void compareMatches(Matches match);

}
