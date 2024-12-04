package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.entities.Rental;
import com.meuteste.Meu.Teste.repositories.BookRepository;
import com.meuteste.Meu.Teste.repositories.PersonRepository;
import com.meuteste.Meu.Teste.repositories.RentalRepository;
import com.meuteste.Meu.Teste.services.RentalService;
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

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<Rental>> findAll() {
        List<Rental> rentalList = rentalService.findAll();
        return ResponseEntity.ok(rentalList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Rental> findById(@PathVariable Long id) {
        Optional<Rental> rental = rentalService.findById(id);
        if (!rental.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(rental.get());
    }

    @PostMapping
    public ResponseEntity<Rental> save(@RequestBody @Valid Rental rental) {
        Rental savedRental = rentalService.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = rentalService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody @Valid Rental rental) {
//        if (!bookRepository.existsById(rental.getBook().getId())){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
//        }
//        if (!personRepository.existsById(rental.getPerson().getId())){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found");
//        }
//
//        Long countAvailableBookCopies = rentalRepository.countAvailableBooksById(rental.getBook().getId());
//        Book book = bookRepository.findById(rental.getBook().getId()).get();
//
//        if (countAvailableBookCopies >= book.getTotalCopies()) {
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("No available copies for this book");
//        }
//
//        Person person = personRepository.findById(rental.getPerson().getId()).get();
//        rental.setPerson(person);
//        rental.setBook(book);
//
//        Rental savedRental = rentalRepository.save(rental);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
//    }
//
//    // PUT -> Atualiza completamente
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<Rental> alter(@PathVariable Long id, @RequestBody Rental rental) {
//        if (!rentalRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        rental.setId(id);
//        Rental updatedRental = rentalRepository.save(rental);
//        return ResponseEntity.ok(updatedRental);
//    }
//
//    // PATCH -> Atualiza parcialmente
//    @PatchMapping(value = "/{id}")
//    public ResponseEntity<Rental> alterPartial(@PathVariable Long id, @RequestBody Rental partialRental) {
//        Optional<Rental> existingRental = rentalRepository.findById(id);
//        if (existingRental.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        Rental rental = existingRental.get();
//
//        if (partialRental.getBook() != null) {
//            rental.setBook(partialRental.getBook());
//        }
//        if (partialRental.getPerson() != null) {
//            rental.setPerson(partialRental.getPerson());
//        }
//        if (partialRental.getRental_status() != null) {
//            rental.setRental_status(partialRental.getRental_status());
//        }
//        Rental updatedRental = rentalRepository.save(rental);
//        return ResponseEntity.ok(updatedRental);
//    }

}
