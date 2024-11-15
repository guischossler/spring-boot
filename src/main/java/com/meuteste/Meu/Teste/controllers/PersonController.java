package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.repositories.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/persons")
/*
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    agora não preciso mais usar essa anotacao,
    pois o arquivo index.html esta em ./resources/static/index.html
    e possivel acessar via http://localhost:8080/
 */
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = repository.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid Person person) {
        Person savedPerson = repository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
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
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody @Valid Person person) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        person.setId(id);
        Person updatedPerson = repository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Person> partialUpdate(@PathVariable Long id, @RequestBody Person person) {
        Optional<Person> existingPersonOpt = repository.findById(id);
        if (existingPersonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Person existingPerson = existingPersonOpt.get();

        if (person.getName() != null) {
            existingPerson.setName(person.getName());
        }
        if (person.getEmail() != null) {
            existingPerson.setEmail(person.getEmail());
        }

        repository.save(existingPerson);
        return ResponseEntity.ok(existingPerson);
    }

}