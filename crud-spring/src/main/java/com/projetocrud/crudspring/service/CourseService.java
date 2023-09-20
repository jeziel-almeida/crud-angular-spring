package com.projetocrud.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetocrud.crudspring.exception.RecordNotFoundException;
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
        Course course = courseRepository.findItemByName(name);
        if(course == null) {
            throw new RecordNotFoundException(name);
        }
        return course;
    }

    public Course getById(@PathVariable @NotNull String id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@PathVariable @NotNull String id, @Valid Course course) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.getName());
                recordFound.setCategory(course.getCategory());
                return courseRepository.save(recordFound);
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull String id) {
        courseRepository.findById(id)
        .map(recordFound -> {
            recordFound.setStatus("Inativo");
            courseRepository.save(recordFound);
            return true;
        })
        .orElseThrow(() -> new RecordNotFoundException(id));
    }
}
