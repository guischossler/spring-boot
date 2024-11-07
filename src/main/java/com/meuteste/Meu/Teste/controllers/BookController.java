package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookRepository repository;

    @GetMapping
    public List<Book> findAll () {
        List<Book> books = repository.findAll();
        return books;
    }

    @GetMapping(value = "/{id}")
    public Book findById (@PathVariable Long id) {
        Book book = repository.findById(id).get();
        return book;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid Book book) {
        try {
            repository.save(book);
            return ResponseEntity.ok("Valid book => " + book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving the book: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) { repository.deleteById(id); }

      /*
        Faz a att dos dados somente se o ID existe, mas se o ID não existe
        ele apenas retorna a Entidade com os dados passados no Body

        Obs: como é um PUT, por exemplo, se passar somente o ID e Name, o autor será salvo como null
     */
    @PutMapping
    public Book alter(@RequestBody @Valid Book book) {
        if (repository.existsById(book.getId())) {
            return repository.save(book);
        }

        return null;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleArgumentException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
