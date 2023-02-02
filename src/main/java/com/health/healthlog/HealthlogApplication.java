package com.health.healthlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HealthlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthlogApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello! this is health log.";
	}
}
