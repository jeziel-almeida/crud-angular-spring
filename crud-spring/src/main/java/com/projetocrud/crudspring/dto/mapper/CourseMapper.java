package com.projetocrud.crudspring.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projetocrud.crudspring.dto.CourseDTO;
import com.projetocrud.crudspring.dto.LessonDTO;
import com.projetocrud.crudspring.enums.Category;
import com.projetocrud.crudspring.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessonsDTO = course.getLessons()
            .stream()
            .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
            .toList();

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessonsDTO);
    }

    public Course toEntity(CourseDTO courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "front-end" -> Category.FRONT_END;
            case "back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };    
    }
}
