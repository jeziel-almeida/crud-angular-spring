package com.projetocrud.crudspring.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document("courses")
public class Course {
    
    @Id
    private String id;

    private String name;

    private String category;
}
