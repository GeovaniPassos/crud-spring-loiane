package com.loiane_cursos.crud_spring_api;

import com.loiane_cursos.crud_spring_api.enums.Category;
import com.loiane_cursos.crud_spring_api.model.Course;
import com.loiane_cursos.crud_spring_api.model.Lesson;
import com.loiane_cursos.crud_spring_api.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CrudSpringApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApiApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner initDataBase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for(int i = 0; i < 20; i++ ) {
				Course c = new Course();
				c.setName("Angular com Spring " + i);
				c.setCategory(Category.FRONT_END);

				Lesson l1 = new Lesson();
				l1.setName("Introdução");
				l1.setYoutubeUrl("watch?v=Nb4");
				l1.setCourse(c);
				c.getLessons().add(l1);

				Lesson l2 = new Lesson();
				l2.setName("Aula " + i);
				l2.setYoutubeUrl("watch?v=Nb4");
				l2.setCourse(c);
				c.getLessons().add(l2);

				courseRepository.save(c);
				// courseRepository.save(c1);
			}
		};
	}

	//Incluir projeto no github
}
