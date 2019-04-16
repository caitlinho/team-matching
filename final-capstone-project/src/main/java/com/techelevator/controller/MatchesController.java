package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.RandomGeneratorDAO;
import com.techelevator.model.Student;

@Controller
@SessionAttributes("currentUser")
public class MatchesController {
	
	@Autowired 
	RandomGeneratorDAO randomGeneratorDao;
	
	@RequestMapping(path="/users/{userName}/teams", method=RequestMethod.GET)
	public String viewAllPreviousMethods(@PathVariable String userName) {
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
	
	

}
