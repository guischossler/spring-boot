package com.meuteste.Meu.Teste.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field not provided")
    private String title;

    @NotBlank(message = "Field not provided")
    private String author;

    @NotNull(message = "Field must not be null")
    @Min(value = 0, message = "Field must be equal to or greater than zero")
    private Integer totalCopies;

}
