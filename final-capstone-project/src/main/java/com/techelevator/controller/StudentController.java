package com.techelevator.controller;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.Student;
import com.techelevator.model.StudentDAO;

@Controller
@SessionAttributes("currentUser")
public class StudentController {

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	StudentDAO studentDao;
	
	@RequestMapping(path="/users/{userName}/{classId}", method=RequestMethod.GET)
	public String showStudentsOfOneClass(@PathVariable String userName, @PathVariable int classId, ModelMap map) {
		map.addAttribute("studentsByClass", studentDao.getStudentsbyClassId(classId));
		return "students";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/{studentId}", method=RequestMethod.GET)
	public String showStudentsOfOneClassEdit(@PathVariable String userName, @PathVariable int classId, @PathVariable int studentId, ModelMap map) {
		map.addAttribute("student", studentDao.getStudentById(studentId));
		return "editStudent";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/{studentId}", method=RequestMethod.POST)
	public String selectingStudentToEdit(@PathVariable String userName, @PathVariable int classId, 
														 @Valid @ModelAttribute("editStudent")Student student, ModelMap map) {
		map.addAttribute("student", student);
		studentDao.editStudent(student);
		
		return "redirect:/users/{userName}/{classId}/";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/addStudent", method=RequestMethod.GET)
	public String displayAddStudentForm(@PathVariable String userName, @PathVariable int classId) {
		return "addStudent";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/addStudent", method=RequestMethod.POST)
	public String addStudentForm(@PathVariable String userName, @PathVariable int classId, @Valid @ModelAttribute("addStudent")Student student, BindingResult result) {
		
		if(result.hasErrors()) {
			return "addStudent";
		}
		studentDao.addStudent(student, classId);
		return "redirect:/users/"+userName+"/"+classId;
	}
	
}
