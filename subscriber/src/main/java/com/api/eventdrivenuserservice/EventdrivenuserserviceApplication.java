package com.api.eventdrivenuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.lang.Exception;

import org.springframework.context.annotation.ComponentScan;

import lombok.Getter;
import lombok.Setter;


import jakarta.jms.Session;

@SpringBootApplication
@Getter
@Setter
@ComponentScan(basePackages = "com.api.eventdrivenuserservice")
public class EventdrivenuserserviceApplication {	
	Session session;
	public static void main(String[] args)  throws Exception{
		SpringApplication.run(EventdrivenuserserviceApplication.class, args);

	}

}
