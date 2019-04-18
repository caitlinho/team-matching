package com.techelevator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.Matches;
import com.techelevator.model.MatchesDAO;
import com.techelevator.model.RandomGeneratorDAO;
import com.techelevator.model.Student;
import com.techelevator.model.StudentDAO;

@Controller
@SessionAttributes("currentUser")
public class MatchesController {
	
	@Autowired
	MatchesDAO matchesDao;
	
	@Autowired
	StudentDAO studentDao;
	
	
	@RequestMapping(path="/users/{userName}/teams", method=RequestMethod.GET)
	public String viewAllPreviousMethods(@PathVariable String userName) {
		matchesDao.getMatchesbyUsername(userName);
		return "previousMatches";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/pairs", method=RequestMethod.GET)
	public String viewGenerateMatchesPage(@PathVariable String userName, @PathVariable int classId, ModelMap map) {
		map.addAttribute("size", 3);
		map.addAttribute("countOfMatch", 4);
		return "generateMatches";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/pairs", method=RequestMethod.POST)
	public String generateMatches(@PathVariable String userName, @PathVariable int classId, @RequestParam("weekOfMatch") int week, @RequestParam("size") int size, @RequestParam("countOfMatch") int countOfMatches, ModelMap map) {
		List<Student> studentsToMatch = studentDao.getStudentsbyClassId(classId);
		studentsToId(studentsToMatch);
		Collections.shuffle(studentsToMatch);
		Matches shuffledMatches = new Matches();
		List<Matches> listOfMatches = new ArrayList<>();
		shuffledMatches.setWeek(week);
		shuffledMatches.setSize(size);
		shuffledMatches.setCount(countOfMatches);
		if(shuffledMatches.getSize() == 2) {
			for(int i = 0; i < studentsToMatch.size(); i += 2) {
				shuffledMatches = new Matches();
				shuffledMatches.setWeek(week);
				shuffledMatches.setSize(size);
				shuffledMatches.setCount(countOfMatches);
				shuffledMatches.setStudentId1(studentsToMatch.get(i).getStudentId());
				shuffledMatches.setStudentId2(studentsToMatch.get(i + 1).getStudentId());
				matchesDao.compareMatches(shuffledMatches);
				listOfMatches.add(shuffledMatches);
			}
		} else {
			for(int i = 0; i < studentsToMatch.size(); i += 3) {
				shuffledMatches = new Matches();
				shuffledMatches.setWeek(week);
				shuffledMatches.setSize(size);
				shuffledMatches.setCount(countOfMatches);
				shuffledMatches.setStudentId1(studentsToMatch.get(i).getStudentId());
				shuffledMatches.setStudentId2(studentsToMatch.get(i + 1).getStudentId());
				shuffledMatches.setStudentId3(studentsToMatch.get(i + 2).getStudentId());
				if(matchesDao.compareMatches(shuffledMatches) == true) {
					listOfMatches.add(shuffledMatches);
				}
				else {
					System.out.print("Team has exceeded match limit");
				}	
			}
		}
		map.addAttribute("newMatches", listOfMatches);

		return "acceptMatches";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/pairs/accept", method=RequestMethod.GET)
	public String viewNewMatches(@PathVariable String userName, @PathVariable int classId) {
		return "acceptMatches";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/pairs/accept", method=RequestMethod.POST)
	public String acceptMatches(@PathVariable String userName, @PathVariable int classId) {
		
		return "redirect:/users/{userName}/{classId}";
	}
	
	private List<Integer> studentsToId(List<Student> studentListToMatch) {
		List<Integer> studentIdList = new ArrayList<>();
		for( Student student : studentListToMatch) {
			studentIdList.add(student.getStudentId());
		}
		return studentIdList;		
	}
	
	private List<Integer> idToStudentPairs(List<Matches> listOfMatches){
		List<Integer> studentIdList = new ArrayList<>();
		for(Matches matches : listOfMatches) {
			studentIdList.add(matches.getStudentId1());
			studentIdList.add(matches.getStudentId2());
		}
		return studentIdList;
	}
	
	private List<Integer> idToStudentTriples(List<Matches> listOfMatches){
		List<Integer> studentIdList = new ArrayList<>();
		for(Matches matches : listOfMatches) {
			studentIdList.add(matches.getStudentId1());
			studentIdList.add(matches.getStudentId2());
			studentIdList.add(matches.getStudentId3());
		}
		return studentIdList;
	}
}
