package com.projetocrud.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Varchar(200) e NOT NULL
    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String category;
}
