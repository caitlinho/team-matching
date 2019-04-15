package com.techelevator.model;

import java.sql.Array;
import java.util.List;

public interface RandomGeneratorDAO {
	
	List<Array> totalCombinations(int classId, int groupSize);

	List<Array> listWeekMatches(int weekId);
	
	void saveMatches(List<Array> matches);
	
	void addOneToCount(List<Array> totalCombinations, List<Array> matches, int countParam);
}
