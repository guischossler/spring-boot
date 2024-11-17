package com.meuteste.Meu.Teste.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Data
@Entity
@Table(name = "tb_persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field not provided")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "The field must contain only letters and spaces")
    private String name;
    @Email(message = "Invalid field value")
    @NotBlank(message = "Field not provided")
    private String email;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
