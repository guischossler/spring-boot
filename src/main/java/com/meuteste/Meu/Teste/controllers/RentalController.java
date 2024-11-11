package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Rental;
import com.meuteste.Meu.Teste.exceptions.RentalExceptionHandler;
import com.meuteste.Meu.Teste.repositories.RentalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (rental == null) new RentalExceptionHandler();
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
    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
