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
//		for(int i = 1; i < studentsToMatch.size(); i++) {
//			if(shuffledMatches.getSize() == 2) {
//				shuffledMatches.setStudentId1(i);
//				shuffledMatches.setStudentId2(i + 1);
//			} else {
//				shuffledMatches.setStudentId1(i);
//				shuffledMatches.setStudentId2(i + 1);
//				shuffledMatches.setStudentId3(i + 2);
//			}
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
				shuffledMatches.setStudentId1(studentsToMatch.get(i).getStudentId());
				shuffledMatches.setStudentId2(studentsToMatch.get(i + 1).getStudentId());
				shuffledMatches.setStudentId3(studentsToMatch.get(i + 2).getStudentId());
				matchesDao.compareMatches(shuffledMatches);
				listOfMatches.add(shuffledMatches);
			}
//			matchesDao.compareMatches(shuffledMatches);
//			listOfMatches.add(shuffledMatches);
			
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
	
	private List<String> studentListToNameStrings(List<Student> studentList) {
		List<String> studentNames = new ArrayList<>();
		for(Student student : studentList) {
			String name = student.getName();
			studentNames.add(name);
		}
		return studentNames;
	}
	
	private List<Integer> studentsToId(List<Student> studentListToMatch) {
		List<Integer> studentIdList = new ArrayList<>();
		for( Student student : studentListToMatch) {
			studentIdList.add(student.getStudentId());
		}
		return studentIdList;		
	}
	
	

}
