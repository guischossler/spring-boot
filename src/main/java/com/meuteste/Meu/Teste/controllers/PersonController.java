package com.meuteste.Meu.Teste.controllers;

import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.repositories.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> save(@RequestBody @Valid Person person) {
        return ResponseEntity.ok("Pessoa válida => " + person.toString());
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