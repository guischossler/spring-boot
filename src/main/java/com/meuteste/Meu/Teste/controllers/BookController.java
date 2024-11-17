package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {


    private final BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> books = repository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Optional<Book> book = repository.findById(id);
        if (!book.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(book.get());
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody @Valid Book book) {
        Book savedBook = repository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
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
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody @Valid Book book) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        book.setId(id);
        Book updatedBook = repository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Book> partialUpdate(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> existingBookOpt = repository.findById(id);
        if (existingBookOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Book existingBook = existingBookOpt.get();

        if (book.getName() != null) {
            existingBook.setName(book.getName());
        }
        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }

        if (book.getTotalCopies() != null) {
            existingBook.setTotalCopies(book.getTotalCopies());
        }

        repository.save(existingBook);
        return ResponseEntity.ok(existingBook);
    }
}
