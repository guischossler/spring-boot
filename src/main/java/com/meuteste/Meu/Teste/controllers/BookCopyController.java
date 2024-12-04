package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.BookCopy;
import com.meuteste.Meu.Teste.services.BookCopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/copys")
public class BookCopyController {

    private final BookCopyService bookCopyService;

    @Autowired
    public BookCopyController (BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    @GetMapping
    public ResponseEntity<List<BookCopy>> findAll() {
        List<BookCopy> bookCopyList = bookCopyService.findAll();
        return ResponseEntity.ok(bookCopyList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookCopy> findById(@PathVariable Long id) {
        Optional<BookCopy> bookCopyOpt = bookCopyService.findById(id);
        if (bookCopyOpt.isPresent()) {
            return ResponseEntity.ok(bookCopyOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
