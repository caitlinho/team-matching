package com.techelevator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.RandomGeneratorDAO;
import com.techelevator.model.Student;

@Controller
@SessionAttributes("currentUser")
public class MatchesController {
	
	@Autowired 
	RandomGeneratorDAO randomGeneratorDao;
	
	
	
	private List<String> studentListToNameStrings(List<Student> studentList) {
		List<String> studentNames = new ArrayList<>();
		for(Student student : studentList) {
			String name = student.getName();
			studentNames.add(name);
		}
		return studentNames;
	}
	
	

}
