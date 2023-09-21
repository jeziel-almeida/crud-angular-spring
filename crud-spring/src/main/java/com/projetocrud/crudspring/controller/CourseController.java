package com.projetocrud.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

import com.projetocrud.crudspring.dto.CourseDTO;
import com.projetocrud.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> list() {
        return courseService.list();
    }

    @GetMapping("/name/{name}")
    public CourseDTO getByName(@PathVariable @NotNull String name) {
        return courseService.getByName(name);
    }

    @GetMapping("/{id}")
    public CourseDTO getById(@PathVariable @NotNull String id) {
        return courseService.getById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull String id, @RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull String id) {
        courseService.delete(id);
    }
}
