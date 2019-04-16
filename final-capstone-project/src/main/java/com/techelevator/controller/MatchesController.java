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
	RandomGeneratorDAO randomGeneratorDao;
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
	public String generateMatches(@PathVariable String userName, @PathVariable int classId) {
		List<Student> studentsToMatch = studentDao.getStudentsbyClassId(classId);
		studentsToId(studentsToMatch);
		Collections.shuffle(studentsToMatch, new Random());
		Matches shuffledMatches = new Matches();
		for(int i = 0; i < studentsToMatch.size(); i++) {
			if(shuffledMatches.getSize() == 2) {
				shuffledMatches.setStudentId1(i);
				shuffledMatches.setStudentId2(i + 1);
			} else {
				shuffledMatches.setStudentId1(i);
				shuffledMatches.setStudentId2(i + 1);
				shuffledMatches.setStudentId3(i + 2);
			}
			matchesDao.compareMatches(shuffledMatches);
			matchesDao.viewMatches(shuffledMatches);
		}
		return "redirect:/users/{userName}/{classId}/pairs/accept";
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
