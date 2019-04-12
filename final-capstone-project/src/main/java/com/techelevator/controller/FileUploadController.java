package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.techelevator.model.CSVReader;

public class FileUploadController {

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(path= {"/", "/upload"}, method=RequestMethod.GET)
	public String showUploadForm() {
		return "uploadForm";
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam(required=false) String CSRF_TOKEN, @RequestParam MultipartFile file, ModelMap map) {
		
		File filePath = getFilePath();
		String fileName = filePath + File.separator + "testImage";
		
			createImage(file, fileName);
		CSVReader reader = new CSVReader();
		
			
			map.addAttribute("message", "uploaded to: " + fileName);
			return "showFile";
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

	private void createImage(MultipartFile file, String fileName) {
		File image = new File(fileName);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
			
			stream.write(file.getBytes());
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
