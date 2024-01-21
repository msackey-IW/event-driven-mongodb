package com.api.userinfoservice;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

import com.api.userinfoservice.models.User;
import com.api.userinfoservice.service.UserServiceImpl;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class UserInfoServiceApplication implements CommandLineRunner{
    private final UserServiceImpl userService;

	public static void main(String[] args){
		SpringApplication.run(UserInfoServiceApplication.class, args);
 
		
	}

   @Override
    public void run(String... args) {
        try {
            Resource resource = new ClassPathResource("MOCK_DATA.csv");
            parseCsvFile(resource);
        } catch (IOException e) {
            
        }
    }

    private void parseCsvFile(Resource resource) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            List<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                try {
                    String firstName = csvRecord.get("first_name");
                    String lastName = csvRecord.get("last_name");
                    int age = Integer.parseInt(csvRecord.get("age"));
                    String sex = csvRecord.get("sex");

                    User user = new User(firstName, lastName, age, sex);
                    userService.addUser(user);
                } catch (NumberFormatException e) {
                   
                }
            }

            
        }
    }
}
