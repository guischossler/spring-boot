package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Rental;
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
        if (rental.isPresent()) {
            return ResponseEntity.ok(rental.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Rental> save(@RequestBody @Valid Rental rental) {
        Rental savedRental = repository.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
    }

    // PUT -> Atualiza completamente
    @PutMapping(value = "/{id}")
    public ResponseEntity<Rental> alter(@PathVariable Long id, @RequestBody Rental rental) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        rental.setId(id);
        Rental updatedRental = repository.save(rental);
        return ResponseEntity.ok(updatedRental);
    }

    // PATCH -> Atualiza parcialmente
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Rental> alterPartial(@PathVariable Long id, @RequestBody Rental partialRental) {
        Optional<Rental> existingRental = repository.findById(id);
        if (existingRental.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Rental rental = existingRental.get();

        if (partialRental.getBook() != null) {
            rental.setBook(partialRental.getBook());
        }
        if (partialRental.getPerson() != null) {
            rental.setPerson(partialRental.getPerson());
        }

        Rental updatedRental = repository.save(rental);
        return ResponseEntity.ok(updatedRental);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // se deletou com sucesso cfme padrao Rest
    }

}
