package com.meuteste.Meu.Teste.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;


    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", book=" + book +
                ", person=" + person +
                '}';
    }
}
