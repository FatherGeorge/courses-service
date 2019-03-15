package com.example.coursesservice;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CoursesRepository repository) {
		return args -> {
			Stream.of("java", "react").forEach(name -> {
				Course course = new Course(name);
				repository.save(course);
			});
		};
	}
}
