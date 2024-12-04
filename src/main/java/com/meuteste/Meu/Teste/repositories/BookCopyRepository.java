package com.meuteste.Meu.Teste.repositories;

import com.meuteste.Meu.Teste.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    @Query("SELECT COUNT(bc) " +
            "FROM BookCopy bc " +
            "WHERE bc.status = 'RENTED' " +
            "AND bc.book.id = :bookId")
    Integer countRentedCopiesByBookId(@Param("bookId") Long bookId);
}
