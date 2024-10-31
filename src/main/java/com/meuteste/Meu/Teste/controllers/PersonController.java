package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.repositories.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Person save (@RequestBody Person person) {
        return repository.save(person);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        repository.deleteById(id);
    }

    /*
        Faz a att dos dados somente se o ID existe, mas se o ID não existe
        ele apenas retorna a Entidade com os dados passados no Body

        Obs: como é um PUT, por exemplo, se passar somente o ID e Name, o Email será salvo como null 
     */
    @PutMapping
    public Person alter(@RequestBody @Valid Person person) {
        if (repository.existsById(person.getId())) {
            return repository.save(person);
        }

        return null;
    }

}