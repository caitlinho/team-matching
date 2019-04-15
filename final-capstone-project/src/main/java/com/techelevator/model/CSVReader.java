package com.techelevator.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	
    
    public List<Student> readFile(File file) throws IOException {
    	
    	List<Student> studentList = new ArrayList<>();

        try (
        		Reader reader = Files.newBufferedReader(file.toPath());
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
<<<<<<< HEAD
                        .withHeader("Id", "Name", "Email", "Comment")
=======
                        .withHeader("studentId", "Name", "Email", "Comment")
>>>>>>> 32ce65feffaf624267c8039c048d071ee9a721e2
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
            	Student student = new Student();
                String id = csvRecord.get(0);
                int studentId = Integer.parseInt(id);
                student.setStudentId(studentId);
                student.setName(csvRecord.get(1));
                student.setEmail(csvRecord.get(2));
                student.setComment(csvRecord.get(3));
              
                studentList.add(student);
            }       
        }
           catch (IOException e) {
        	   throw e;
           }
        
		return studentList;  
    }     
}