package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Rental;
import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.repositories.BookRepository;
import com.meuteste.Meu.Teste.repositories.RentalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/rentals")
public class RentalController {

    private final RentalRepository repository;

    @Autowired
    public RentalController(RentalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Rental>> findAll() {
        List<Rental> rentals = repository.findAll();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Rental> findById(@PathVariable Long id) {
        Optional<Rental> rental = repository.findById(id);

        if (!rental.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(rental.get());
    }

    @PostMapping
    public ResponseEntity<Rental> save(@RequestBody @Valid Rental rental) {
        Rental savedRental = repository.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Rental> update(@PathVariable Long id, @RequestBody @Valid Rental rental) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        rental.setId(id);
        Rental updatedRental = repository.save(rental);
        return ResponseEntity.ok(updatedRental);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Rental> partialUpdate(@PathVariable Long id, @RequestBody Rental rental) {
        Optional<Rental> existingRentalOpt = repository.findById(id);
        if (existingRentalOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Rental existingRental = existingRentalOpt.get();

        if (rental.getBook() != null && rental.getBook().getId() != null) {
            existingRental.setBook(rental.getBook());
        }
        if (rental.getPerson() != null && rental.getPerson().getId() != null) {
            existingRental.setPerson(rental.getPerson());
        }

        repository.save(existingRental);
        return ResponseEntity.ok(existingRental);
    }

}
