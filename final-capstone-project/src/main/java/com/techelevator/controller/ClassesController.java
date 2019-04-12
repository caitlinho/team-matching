package com.techelevator.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.InstructorClasses;
import com.techelevator.model.InstructorClassesDAO;
import com.techelevator.model.InstructorDAO;

@Controller
@SessionAttributes("currentUser")
public class ClassesController {

	@Autowired
	private InstructorClassesDAO instructorClassesDAO;
	
	@Autowired
	private InstructorDAO instructorDAO;
	
	@RequestMapping(path="/users/{userName}/dashboard", method=RequestMethod.GET)
	public String viewDashboard(@PathVariable 
			String userName, ModelMap map, HttpSession session) {
		map.addAttribute("allClasses", instructorClassesDAO.viewClasses(instructorDAO.getInstructorById(userName)));
		
		return "dashboard";	
	}
	
	@RequestMapping(path="/users/{userName}/addClass", method=RequestMethod.GET)
	public String displayAddClassForm(@PathVariable String userName) {
		return "addClass";
	}
	
	@RequestMapping(path="/users/{userName}/addClass", method=RequestMethod.POST)
	public String addClass(@PathVariable String userName, ModelMap map, @Valid @ModelAttribute("addClass") InstructorClasses newClass, BindingResult result) {
		
		if(result.hasErrors()) {
			return "addClass";
		}
		instructorClassesDAO.addClass(newClass);
		String a = "redirect:/users/"+userName+"/dashboard";
		return a;
	}
}
