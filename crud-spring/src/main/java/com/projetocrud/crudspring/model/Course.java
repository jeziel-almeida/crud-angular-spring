package com.projetocrud.crudspring.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import com.projetocrud.crudspring.enums.Category;
import com.projetocrud.crudspring.enums.Status;
import com.projetocrud.crudspring.enums.converters.CategoryConverter;
import com.projetocrud.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document("courses")
//@SQLDelete(sql = "UPDATE courses SET status = 'Inativo' WHERE id = ?") *Banco de dados relacional
//@Where(clause = "status = 'Ativo'") *Banco de dados relacional
public class Course {

    @Id
    private String id;
    //@GeneratedValue(strategy = GenerationType.AUTO) *Banco de dados relacional
    //JsonProperty("_id")

    @NotBlank //* Pelo menos um caractere que não seja espaço: https://jakarta.ee/specifications/bean-validation/3.0/apidocs/ */
    @NotNull
    @Length(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    // @CreatedDate
    // private Date deletedAt;
}
