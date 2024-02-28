package com.api.eventdrivenuserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.Exception;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.api.eventdrivenuserservice.domain.user.infrastructure.UserRepository;

import lombok.Getter;
import lombok.Setter;


@SpringBootApplication
@Getter
@Setter
@ComponentScan(basePackages = "com.api.eventdrivenuserservice")
@EnableMongoRepositories
public class EventdrivenuserserviceApplication {	

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args)  throws Exception{
		SpringApplication.run(EventdrivenuserserviceApplication.class, args);

	}

}
