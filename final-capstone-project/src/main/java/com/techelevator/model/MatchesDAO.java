package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public interface MatchesDAO {
	
	List<Matches> getMatchesbyClassId(int classId);
	List<Matches> getMatchesbyStudentId(int studentId);
//	void saveMatches(Matches matches); 
	List<Matches> viewMatches(Matches match);
	void compareMatches(Matches match);

}
