package com.projetocrud.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetocrud.crudspring.dto.CourseDTO;
import com.projetocrud.crudspring.dto.mapper.CourseMapper;
import com.projetocrud.crudspring.exception.RecordNotFoundException;
import com.projetocrud.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class CourseService {
    
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        //return courseRepository.findAll();
        return courseRepository.findByActive()
            .stream()
            .map(courseMapper::toDTO)
            .toList();
    }

    public CourseDTO getByName(@PathVariable @NotNull String name) {
        return courseRepository.findItemByName(name)
            .map(courseMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(name));
    }

    public CourseDTO getById(@PathVariable @NotNull String id) {
        return courseRepository.findById(id)
            .map(courseMapper::toDTO)
            .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) { 
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@PathVariable @NotNull String id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.name());
                recordFound.setCategory(course.category());
                return courseMapper.toDTO(courseRepository.save(recordFound));
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
