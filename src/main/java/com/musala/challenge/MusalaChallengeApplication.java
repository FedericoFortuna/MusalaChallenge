package com.musala.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MusalaChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusalaChallengeApplication.class, args);
	}

}
