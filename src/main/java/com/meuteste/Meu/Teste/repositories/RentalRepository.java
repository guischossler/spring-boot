package com.meuteste.Meu.Teste.repositories;

import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(value = "SELECT COUNT(*) " +
            "FROM tb_rentals r " +
            "JOIN tb_books b ON r.book_id = b.id " +
            "WHERE r.rental_status = 'AVAILABLE' " +
            "AND b.id = :id",
            nativeQuery = true)
    long countAvailableBooksById(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) " +
            "FROM tb_rentals r " +
            "JOIN tb_books b ON r.book_id = b.id " +
            "WHERE r.rental_status = 'AVAILABLE' " +
            "AND b.id = :id",
            nativeQuery = true)
    List<?> showAllAvailableBooks(@Param("id") Long id);

    @Query(value = "asd",
    nativeQuery = true)
    Boolean checkIfPersonHasAnyRentalActive(@Param("id") Long id);
}
