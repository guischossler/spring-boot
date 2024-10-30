package com.meuteste.Meu.Teste.repositories;

import com.meuteste.Meu.Teste.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
