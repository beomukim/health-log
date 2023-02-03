package com.health.healthlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaAuditing
@SpringBootApplication
@RestController
public class HealthlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthlogApplication.class, args);
	}
}
