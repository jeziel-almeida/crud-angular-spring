package com.projetocrud.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetocrud.crudspring.model.Course;
import com.projetocrud.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {
    
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Course> getByName(@PathVariable @NotNull String name) {
        Course course = courseRepository.findItemByName(name);
        if(course != null) {
            return ResponseEntity.ok().body(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable @NotNull String id) {
        return courseRepository.findById(id)
            .map(recordFound -> ResponseEntity.ok().body(recordFound))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull String id, @RequestBody @Valid Course course) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.getName());
                recordFound.setCategory(course.getCategory());
                Course updated = courseRepository.save(recordFound);
                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull String id) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                courseRepository.deleteById(id);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }

}
