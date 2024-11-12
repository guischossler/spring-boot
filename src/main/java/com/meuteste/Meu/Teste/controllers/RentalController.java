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

    @Autowired
    RentalRepository repository;

    @GetMapping
    public List<Rental> findAll() {
        List<Rental> rentals = repository.findAll();
        return rentals;
    }

    @GetMapping(value = "/{id}")
    public Rental findById(@PathVariable Long id) {
        Rental rental = repository.findById(id).get();
        return rental;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Rental rental) {
        try {
            repository.save(rental);
            return ResponseEntity.ok("Valid rental => " + rental);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving the rental: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // se deletou com sucesso cfme padrao Rest
    }

}
