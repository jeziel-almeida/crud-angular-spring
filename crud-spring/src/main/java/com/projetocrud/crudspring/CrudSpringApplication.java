package com.projetocrud.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projetocrud.crudspring.enums.Category;
import com.projetocrud.crudspring.model.Course;
import com.projetocrud.crudspring.model.Lesson;
import com.projetocrud.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course course = new Course();
			course.setName("Angular com Spring");
			course.setCategory(Category.BACK_END);

			Lesson lesson = new Lesson();
			lesson.setName("Loiane - OneToMany");
			lesson.setYoutubeUrl("Nb4uxLxdvxo");
			lesson.setCourse(course);
			course.getLessons().add(lesson);

			courseRepository.save(course);
		};
	}

}
