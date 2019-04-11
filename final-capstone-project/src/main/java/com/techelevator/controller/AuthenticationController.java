package com.techelevator.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.techelevator.model.Instructor;
import com.techelevator.model.InstructorClasses;
import com.techelevator.model.InstructorClassesDAO;
import com.techelevator.model.InstructorDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {

	private UserDAO userDAO;
	
	@Autowired
	private InstructorClassesDAO instructorClassesDAO;
	
	@Autowired
	private InstructorDAO instructorDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}
	
	@RequestMapping(path="/", method=RequestMethod.POST)
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						HttpSession session) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
			return "redirect:/users/"+userName+"/dashboard";	
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(path="/users/{userName}/dashboard", method=RequestMethod.GET)
	public String viewDashboard(@PathVariable  String userName, ModelMap map, HttpSession session) {
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
		
		return "redirect:/users/{userName}/dashboard";
	}

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/";
	}
}
