package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.techelevator.model.CSVReader;
import com.techelevator.model.InstructorClassesDAO;
import com.techelevator.model.Student;
import com.techelevator.model.StudentDAO;
import com.techelevator.model.User;

@Controller
@SessionAttributes("currentUser")
public class FileUploadController {

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	StudentDAO studentDao;
	
	@RequestMapping(path= {"/users/{userName}/{classId}/upload"}, method=RequestMethod.GET)
	public String showUploadForm(@PathVariable String userName, @PathVariable int classId, ModelMap map) {
		return "uploadStudents";
	}
	
	@RequestMapping(path="/users/{userName}/{classId}/upload", method=RequestMethod.POST)
	public String handleFileUpload(@PathVariable String userName, @PathVariable int classId, @RequestParam(required=false) String CSRF_TOKEN, @RequestParam MultipartFile file, ModelMap map) throws IOException {
		
		File filePath = getFilePath();
		String fileName = filePath + File.separator + "testImage";
		
		File fileConverted = createImage(file, fileName);
	
		CSVReader reader = new CSVReader();
		List<Student> studentList = reader.readFile(fileConverted);
		
		for (Student student: studentList) {
			studentDao.addStudent(student, classId);
		}
		
			
		map.addAttribute("message", "uploaded to: " + fileName);
		return "redirect:/users/"+userName+"/"+classId;
	}
	
	@RequestMapping(path="/image/{imageName}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable(value="imageName") String imageName) {
		String imagePath = getServerContextPath() + File.separator + imageName;
		File image = new File(imagePath);
		try {
			return Files.readAllBytes(image.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
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

	private File createImage(MultipartFile file, String fileName) {
		File image = new File(fileName);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
			
			stream.write(file.getBytes());
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;	
	}
}
