package com.example.logIngestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class LogIngestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogIngestorApplication.class, args);
	}

}
