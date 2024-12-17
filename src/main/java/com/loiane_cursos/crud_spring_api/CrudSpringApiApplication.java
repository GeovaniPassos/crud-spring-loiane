package com.loiane_cursos.crud_spring_api;

import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory("Front-end");

			Course c1 = new Course();
			c1.setName("Spring Boot");
			c1.setCategory("Back-end");

			courseRepository.save(c);
			courseRepository.save(c1);
		};
	}

	//Incluir projeto no github
}
