package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("currentUser")
public class StudentController {

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(path="/users/{userName}/{classId}/uploadStudentFile", method=RequestMethod.GET)
	public String showUploadForm(@PathVariable String userName, @PathVariable int classId) {
		return "uploadStudents";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/uploadStudentFile", method=RequestMethod.POST)
	public String handleFileUpload(@PathVariable String userName, @RequestParam MultipartFile file, ModelMap map) {
		
		File filePath = getFilePath();
		String fileName = filePath + File.separator + "testFile";
		
		if (file.isEmpty()) {
			map.addAttribute("message", "File Object empty");
		} else {
			createFile(file, fileName);
		}
		return "students";
	}
	
	private File getFilePath() {
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return filePath;
	}
	
	private String getServerContextPath() {
		return servletContext.getRealPath("/") + "uploads";
	}
	
	private void createFile(MultipartFile file, String name) {
		File studentFile = new File(name);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(studentFile))) {
	
			stream.write(file.getBytes());
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
