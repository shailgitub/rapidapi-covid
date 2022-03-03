package com.rapidapi.covid19statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.rapidapi.covid19statistics.repo")
@SpringBootApplication
public class Covid19statisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19statisticsApplication.class, args);
	}

}
