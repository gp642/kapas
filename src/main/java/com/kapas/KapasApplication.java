package com.kapas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KapasApplication {

	private static final Logger logger = LoggerFactory.getLogger(KapasApplication.class);

	public static void main(String[] args) {

		logger.info("Starting Application ...");
		SpringApplication.run(KapasApplication.class, args);
	}

}
