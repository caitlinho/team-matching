package com.techelevator.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	public String viewAllPreviousMethods(@PathVariable String userName, ModelMap map) {
		List<Matches> listOfMatches = matchesDao.getMatchesbyUsername(userName);
		List<String> previousMatchedStudents = new ArrayList<>();
		for(int i = 0; i < listOfMatches.size(); i++) {
			Matches match = new Matches();
			match.setStudentId1(listOfMatches.get(i).getStudentId1());
			match.setStudentId2(listOfMatches.get(i).getStudentId2());
			String name1 = studentDao.getStudentById(match.getStudentId1()).getName();
			String name2 = studentDao.getStudentById(match.getStudentId2()).getName();
			String fullName = name1 + " - " + name2;
			previousMatchedStudents.add(fullName);
		}
		
		map.addAttribute("previousMatches", previousMatchedStudents);
		return "previousMatches";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/pairs", method=RequestMethod.GET)
	public String viewGenerateMatchesPage(@PathVariable String userName, @PathVariable int classId, ModelMap map) {
		return "generateMatches";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/pairs", method=RequestMethod.POST)
	public String generateMatches(@PathVariable String userName, @PathVariable int classId, @RequestParam("weekOfMatch") int week, @RequestParam("size") int size, @RequestParam("countOfMatch") int countOfMatches, ModelMap map, BindingResult results) {
		List<Student> studentsToMatch = studentDao.getStudentsbyClassId(classId);
		studentsToId(studentsToMatch);
		Collections.shuffle(studentsToMatch);
		List<String> matchedStudents = new ArrayList<>();
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
				String name1 = (studentDao.getStudentById(shuffledMatches.getStudentId1()).getName());
				String name2 = (studentDao.getStudentById(shuffledMatches.getStudentId2()).getName());
				String group = name1 + " - " + name2;
				matchedStudents.add(group);
				if(matchesDao.compareMatches(shuffledMatches) == true){
					listOfMatches.add(shuffledMatches);
				} else {
					System.out.print("Team has exceeded match limit");
				}
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
				String name1 = (studentDao.getStudentById(shuffledMatches.getStudentId1()).getName());
				String name2 = (studentDao.getStudentById(shuffledMatches.getStudentId2()).getName());
				String name3 = (studentDao.getStudentById(shuffledMatches.getStudentId3()).getName());
				String group = name1 + " - " + name2 + " - " + name3;
				matchedStudents.add(group);
				if(matchesDao.compareMatches(shuffledMatches) == true) {
					listOfMatches.add(shuffledMatches);
				}
				else {
					if(results.hasErrors()) {
						
					}
				}
			}
		}
		map.addAttribute("newMatches", matchedStudents);

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
}
