package com.meuteste.Meu.Teste.repositories;

import com.meuteste.Meu.Teste.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
