package com.meuteste.Meu.Teste.services;

import com.meuteste.Meu.Teste.entities.Person;
import com.meuteste.Meu.Teste.repositories.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public boolean deleteById(Long id) {
        if (!personRepository.existsById(id)) {
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }

    public Optional<Person> updateAllData(Person person) {
        if (!personRepository.existsById(person.getId())) {
            return Optional.empty();
        }

        Person updatedPerson = personRepository.save(person);
        return Optional.of(updatedPerson);
    }

    public Optional<Person> updateParcialData(Person person) {
        Optional<Person> existingPersonOpt = personRepository.findById(person.getId());
        if (existingPersonOpt.isEmpty()) {
            return Optional.empty();
        }

        Person existingPerson = existingPersonOpt.get();

        if (person.getName() != null) {
            existingPerson.setName(person.getName());
        }
        if (person.getEmail() != null) {
            existingPerson.setEmail(person.getEmail());
        }

        Person updatedPerson = personRepository.save(existingPerson);
        return Optional.of(updatedPerson);
    }
}
