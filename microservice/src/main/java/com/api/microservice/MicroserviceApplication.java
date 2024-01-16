package com.api.microservice;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import com.api.microservice.models.User;
import com.api.microservice.service.UserServiceImpl;

import lombok.AllArgsConstructor;

import java.io.FileReader;
import java.io.Reader;


@AllArgsConstructor
@SpringBootApplication
public class MicroserviceApplication implements CommandLineRunner{
    private final UserServiceImpl userService;

	public static void main(String[] args){
		SpringApplication.run(MicroserviceApplication.class, args);
 
		
	}

    @Override
    public void run(String... args) throws Exception {
        String csvFilePath = new ClassPathResource("MOCK_DATA.csv").getFile().getPath();
        parseCsvFile(csvFilePath);
    }

    private  void parseCsvFile(String csvFilePath) throws Exception {
        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                String firstName = csvRecord.get("first_name");
                String lastName = csvRecord.get("last_name");
                int age = Integer.parseInt(csvRecord.get("age"));
                String sex = csvRecord.get("sex");

                User user = new User(firstName, lastName, age, sex);
  
                userService.addUser(user);
            }
        }
    }

}
