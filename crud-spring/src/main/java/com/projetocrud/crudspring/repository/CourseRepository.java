package com.projetocrud.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetocrud.crudspring.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
