package com.api.eventdrivenuserservice;

import com.api.eventdrivenuserservice.model.User;
import com.api.eventdrivenuserservice.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
@SpringBootApplication
@AllArgsConstructor
public class EventDrivenUserServiceApplication implements CommandLineRunner {
	private final UserServiceImpl userService;
	public static void main(String[] args) {
		SpringApplication.run(EventDrivenUserServiceApplication.class, args);
	}
	@Override
	public void run(String... args) {
		
	}


}
