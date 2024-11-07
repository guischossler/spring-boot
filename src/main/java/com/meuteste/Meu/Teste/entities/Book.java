package com.meuteste.Meu.Teste.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Field not provided")
    private String name;
    @NotBlank(message = "Field not provided")
    private String author;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book(");
        if (id != null) sb.append("id=," + id+",");
        sb.append("name="+name+",");
        sb.append("author="+author+")");

        return sb.toString();
    }

}
