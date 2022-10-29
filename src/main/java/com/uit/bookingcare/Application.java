package com.uit.bookingcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.uit.bookingcare.repository"
})
//@EnableJpaAuditing(auditorAwareRef = "jpaAuditorProvider")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
