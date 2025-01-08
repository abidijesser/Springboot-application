package org.example.abidijasser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MindsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindsSpringBootApplication.class, args);
	}

}
