package com.projetocrud.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetocrud.crudspring.model.Course;
import com.projetocrud.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class CourseService {
    
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        //return courseRepository.findAll();
        return courseRepository.findByActive();
    }

    public Course getByName(@PathVariable @NotNull String name) {
        return courseRepository.findItemByName(name);
    }

    public Optional<Course> getById(@PathVariable @NotNull String id) {
        return courseRepository.findById(id);
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> update(@PathVariable @NotNull String id, @Valid Course course) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.getName());
                recordFound.setCategory(course.getCategory());
                return courseRepository.save(recordFound);
            });
    }

    public boolean delete(@PathVariable @NotNull String id) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setStatus("Inativo");
                courseRepository.save(recordFound);
                return true;
            })
            .orElse(false);
    }
}
