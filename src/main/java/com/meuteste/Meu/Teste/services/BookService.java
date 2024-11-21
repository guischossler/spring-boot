package com.meuteste.Meu.Teste.services;

import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        if (!bookRepository.existsById(id)) {
            return Optional.empty();
        }
        Book book = bookRepository.findById(id).get();
        return Optional.of(book);
    }

    public Book save(Book book) {
       return bookRepository.save((book));
    }

    public boolean deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

    public Optional<Book> updateAllData(Book book) {
        if (!bookRepository.existsById(book.getId())) {
            return Optional.empty();
        }

        Book updatedBook = bookRepository.save(book);
        return Optional.of(updatedBook);
    }

    public Optional<Book> updateParcialData(Book book) {
        Optional<Book> existingBookOpt = bookRepository.findById(book.getId());
        if (existingBookOpt.isEmpty()) {
            return Optional.empty();
        }

        Book existingBook = existingBookOpt.get();

        if (book.getTitle() != null) {
            existingBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }
        if (book.getTotalCopies() != null) {
            existingBook.setTotalCopies(book.getTotalCopies());
        }

        Book updatedBook = bookRepository.save(existingBook);
        return Optional.of(updatedBook);
    }

}
