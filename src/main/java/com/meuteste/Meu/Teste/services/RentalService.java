package com.meuteste.Meu.Teste.services;

import com.meuteste.Meu.Teste.entities.Rental;
import com.meuteste.Meu.Teste.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> findAll() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> findById(Long id) {
        if (!rentalRepository.existsById(id)) {
            return Optional.empty();
        }
        Rental rental = rentalRepository.findById(id).get();
        return Optional.of(rental);
    }

    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    public boolean deleteById(Long id) {
        if (!rentalRepository.existsById(id)) {
            return false;
        }
        rentalRepository.deleteById(id);
        return true;
    }
}
