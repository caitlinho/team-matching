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
import com.techelevator.model.Matches;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class ClassesController {

	@Autowired
	private InstructorClassesDAO instructorClassesDAO;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(path="/users/{userName}/dashboard", method=RequestMethod.GET)
	public String viewDashboard(@PathVariable 
			String userName, ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		map.addAttribute("allClasses", instructorClassesDAO.viewClasses(user.getId()));
		
		return "dashboard";	
	}
	
	@RequestMapping(path="/users/{userName}/addClass", method=RequestMethod.GET)
	public String displayAddClassForm(@PathVariable String userName) {
		return "addClass";
	}
	
	@RequestMapping(path="/users/{userName}/addClass", method=RequestMethod.POST)
	public String addClass(@PathVariable String userName, ModelMap map, @Valid @ModelAttribute("addClass") InstructorClasses newClass, BindingResult result, HttpSession session,
			@RequestParam("weekOfMatch") int week, @RequestParam("size") int size, @RequestParam("countOfMatch") int countOfMatch) {
		
		if(result.hasErrors()) {
			return "addClass";
		}
		User user = (User) session.getAttribute("currentUser");
		int id = user.getId();
		instructorClassesDAO.addClass(newClass, id);
		return "redirect:/users/"+userName+"/dashboard";
	}
}
