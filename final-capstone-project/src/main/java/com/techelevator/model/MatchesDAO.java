package com.techelevator.model;

import java.util.List;

public interface MatchesDAO {
	
	List<Matches> generateMatches();
	List<Matches> editMatches();
	List<Matches> viewMatches();
	void updateParameters();

}
