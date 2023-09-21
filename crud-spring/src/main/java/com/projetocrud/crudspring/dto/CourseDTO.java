package com.projetocrud.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
    String id,
    @NotBlank @NotNull @Length(min = 5, max = 100) String name,
    @NotNull @Length(max = 10) @Pattern(regexp = "front-end|back-end") String category
) {
    
}
