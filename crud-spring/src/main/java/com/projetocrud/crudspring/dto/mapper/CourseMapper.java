package com.projetocrud.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.projetocrud.crudspring.dto.CourseDTO;
import com.projetocrud.crudspring.enums.Category;
import com.projetocrud.crudspring.model.Course;

@Component
public class CourseMapper {
    
    public CourseDTO toDTO(Course course) {
        if(course == null) return null;
        
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDto) {
        if(courseDto == null) return null;

        Course course = new Course();
        if(courseDto.id() != null) course.setId(courseDto.id());
        course.setName(courseDto.name());
        course.setCategory(convertValueToCategory(courseDto.category()));
        return course;
    }

    public Category convertValueToCategory(String value) {
        if(value == null) return null;

        return switch (value) {
            case "front-end" -> Category.FRONT_END;
            case "back-end" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }
}
