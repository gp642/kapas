package com.kapas;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(
		value = "com.kapas",
		repositoryBaseClass = BaseJpaRepositoryImpl.class
)
public class KapasApplication {

	private static final Logger logger = LoggerFactory.getLogger(KapasApplication.class);

	public static void main(String[] args) {

		logger.info("Starting Application ...");
		SpringApplication.run(KapasApplication.class, args);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
