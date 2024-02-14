package com.api.eventdrivenuserservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.api.eventdrivenuserservice.*")
@ComponentScan(basePackages = { "com.api.eventdrivenuserservice.*" })
@EntityScan("com.api.eventdrivenuserservice.*")  
@SpringBootApplication
public class EventDrivenUserServiceApplication implements CommandLineRunner {
	public static void main(String... args) throws Exception {
		SpringApplication.run(EventDrivenUserServiceApplication.class, args);

	}
	@Override
	public void run(String... args) {
	}




}
