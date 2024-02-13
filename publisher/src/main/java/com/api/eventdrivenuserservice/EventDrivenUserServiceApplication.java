package com.api.eventdrivenuserservice;


import com.api.eventdrivenuserservice.eda.PersonTopicSubscriber;
import com.api.eventdrivenuserservice.service.UserService;
import com.api.eventdrivenuserservice.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Getter
@Setter
@ComponentScan(basePackages = "com.api.eventdrivenuserservice")
@AllArgsConstructor
public class EventDrivenUserServiceApplication implements CommandLineRunner {
	public static void main(String... args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(EventDrivenUserServiceApplication.class, args);

	}
	@Override
	public void run(String... args) {
	}




}
