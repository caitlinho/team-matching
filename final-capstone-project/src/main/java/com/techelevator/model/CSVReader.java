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
	
    private static final String SAMPLE_CSV_FILE_PATH = "./StudentList.csv";
    
    public List<Student> readFile(File file) throws IOException {
    	
    	List<Student> studentList = new ArrayList<>();

        try (
        		Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader("Name", "Email", "Phone", "Country")
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
//                studentList.addAll(csvRecord);
                // Accessing Values by Column Index
                String id = csvRecord.get(0);
                String name = csvRecord.get(1);
                String email = csvRecord.get(2);
                String comment = csvRecord.get(3);
            }
        
            
        }
           catch (IOException e) {
        	   throw e;
           }
        
		return studentList;
        
    }
         
}