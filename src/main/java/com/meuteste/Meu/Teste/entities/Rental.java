package com.meuteste.Meu.Teste.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    // AVAILABLE, RENTED, RETURNED
    @NotBlank
    private String rental_status;

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", book_id=" + book +
                ", person_id=" + person +
                ", rental_status=" + rental_status +
                '}';
    }

}
