package com.projetocrud.crudspring.repository;

import java.util.List;

//import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetocrud.crudspring.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    
    @Query("{name: '?0'}")
    Course findItemByName(String name);

    // @Query(value = "{category:'?0'}", fields = "{'name' : 1}")
    // List<Course> findByCategory(String category);

    @Query(value = "{status:'Ativo'}")
    List<Course> findByActive();

    public long count();
}
