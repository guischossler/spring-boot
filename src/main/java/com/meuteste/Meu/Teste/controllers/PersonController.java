package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    CORS: agora não preciso mais usar essa anotacao,
    pois o arquivo index.html esta em ./resources/static/index.html
    e possivel acessar via http://localhost:8080/
 */
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping(value = "/api/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            return ResponseEntity.ok(person.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean deleted = personService.deleteById(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody @Valid Person person) {
        person.setId(id); // para garantir que é o mesmo id enviado no path
        Optional<Person> updatedPersonOpt = personService.updateAllData(person);
        if (updatedPersonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedPersonOpt.get());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Person> partialUpdate(@PathVariable Long id, @RequestBody Person partialPerson) {
        partialPerson.setId(id); // para garantir que é o mesmo id enviado no path
        Optional<Person> updatedPersonOpt = personService.updateParcialData(partialPerson);
        if (updatedPersonOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedPersonOpt.get());
    }

}