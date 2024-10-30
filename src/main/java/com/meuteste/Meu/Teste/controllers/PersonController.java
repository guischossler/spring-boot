package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/persons")
public class PersonController {

    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> findAll() {
        List<Person> persons = repository.findAll();
        return persons;
    }

    @GetMapping(value = "/{id}")
    public Person findById (@PathVariable Long id) {
        Person person = repository.findById(id).get();
        return person;
    }

}
