package com.health.healthlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // created_at, updated_at default 설정
@SpringBootApplication
@ConfigurationPropertiesScan
public class HealthlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthlogApplication.class, args);
	}
}
