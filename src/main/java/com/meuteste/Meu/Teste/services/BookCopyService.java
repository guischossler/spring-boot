package com.meuteste.Meu.Teste.services;

import com.meuteste.Meu.Teste.entities.BookCopy;
import com.meuteste.Meu.Teste.repositories.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    
    @Autowired
    public BookCopyService(BookCopyRepository bookCopyRepository) {
        this.bookCopyRepository = bookCopyRepository;
    }

    public List<BookCopy> findAll() {
        return bookCopyRepository.findAll();
    }

    public Optional<BookCopy> findById(Long id) {
        if (!bookCopyRepository.existsById(id)) {
            return Optional.empty();
        }
        BookCopy bookCopy = bookCopyRepository.findById(id).get();
        return Optional.of(bookCopy);
    }

    // Utilizado no BookService, para criar as copias dos livros
    public BookCopy save(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    /* O delete, precisa ver se tem alguma copia alugada,
     * o BookService tbm precisa olhar aqui, pra ver se ela pode apagar
     */
    public boolean canDeleteBook(Long bookId) {
        // Verifica se há cópias alugadas, se nao existir copias alugadas, retorna true
        return bookCopyRepository.countRentedCopiesByBookId(bookId) == 0;
    }


}
