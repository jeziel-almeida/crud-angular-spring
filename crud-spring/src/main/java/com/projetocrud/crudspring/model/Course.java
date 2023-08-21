package com.projetocrud.crudspring.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Document("courses")
public class Course {

    @Id
    private String id;
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //JsonProperty("_id")

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String category;
}
