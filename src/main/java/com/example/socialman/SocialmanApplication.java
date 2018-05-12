package com.example.socialman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SocialmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialmanApplication.class, args);
	}
}
