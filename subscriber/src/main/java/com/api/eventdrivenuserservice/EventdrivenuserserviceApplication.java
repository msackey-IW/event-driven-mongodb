package com.api.eventdrivenuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.AllArgsConstructor;
import com.api.eventdrivenuserservice.domain.user.ports.outgoing.UserEventSubscriber;
import org.springframework.boot.CommandLineRunner;
import java.lang.Exception;
import com.api.eventdrivenuserservice.domain.user.core.ports.incoming.AddNewUser;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ComponentScan;
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


@AllArgsConstructor
@SpringBootApplication
@Getter
@Setter
@ComponentScan(basePackages = "com.api.eventdrivenuserservice")
@AllArgsConstructor
public class EventdrivenuserserviceApplication implements CommandLineRunner {
	private final UserEventSubscriber userEventSubscriber;
	public static void main(String[] args)  {
		ConfigurableApplicationContext context = SpringApplication.run(EventdrivenuserserviceApplication.class, args);
		AddNewUser addNewUser = context.getBean(AddNewUser.class);
		UserEventSubscriber subscriber = new UserEventSubscriber(addNewUser);
		subscriber.run();

	}

	@Override
	public void run(String... args) throws Exception {

	}

}
