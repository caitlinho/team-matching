package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(path="/users/{userName}/{classId}", method=RequestMethod.POST)
	public String selectingStudentToEdit(@PathVariable String userName, @PathVariable int classId, 
														@RequestParam int studentId, ModelMap map) {
		
		return "redirect:/users/{userName}/{classId}/"+studentId;
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
