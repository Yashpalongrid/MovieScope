package com.onboarding.moviescope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MovieScopeApplication  {

	public static void main(String[] args) {
		SpringApplication.run(MovieScopeApplication.class, args);
	}

}







