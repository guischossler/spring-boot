package com.meuteste.Meu.Teste.services;

import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.entities.Book;
import com.meuteste.Meu.Teste.entities.BookCopy;
import com.meuteste.Meu.Teste.repositories.BookCopyRepository;
import com.meuteste.Meu.Teste.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // para que seja realizada a criação de cada copia, que depois serao alugadas no rental
    private final BookCopyRepository bookCopyRepository;

    // para que seja verificado se é possível deletar
    private final BookCopyService bookCopyService;

    @Autowired
    public BookService(BookRepository bookRepository, BookCopyRepository bookCopyRepository, BookCopyService bookCopyService) {
        this.bookRepository = bookRepository;
        this.bookCopyRepository = bookCopyRepository;
        this.bookCopyService = bookCopyService;
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
//       return bookRepository.save(book);
        Book savedBook = bookRepository.save(book);

        // for para criar as copias
        for (int i = 0; i < book.getTotalCopies(); i++) {
            BookCopy bookCopy = new BookCopy();
            bookCopy.setBook(book);
            bookCopy.setStatus("AVAILABLE");

            bookCopyRepository.save(bookCopy);
        }

        return savedBook;
    }

    public boolean deleteById(Long id) {
        if (!bookRepository.existsById(id)) {
            return false; // Livro não existe
        }

        // Verifica se é permitido deletar com base em cópias alugadas
        if (!bookCopyService.canDeleteBook(id)) {
            return false; // Não pode deletar
            // porém, não é descrito no bookController que nao pode deletar, apenas retorna not found
        }

        // Deleta o livro
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
            /*
             * se o valor de total de copias for MAIOR do que o já existente,
             * precisa criar novas copias na tabela book_copy
             *
             * obs: avaliar como proceder quando o numero é MENOR
             */

            int newTotalCopies = book.getTotalCopies();
            int existingTotalCopies = existingBook.getTotalCopies();
            existingBook.setTotalCopies(newTotalCopies);

            if (newTotalCopies > existingTotalCopies) {
                int copiesToCreate = newTotalCopies - existingTotalCopies;

                for (int i = 0; i < copiesToCreate; i++) {
                    BookCopy newCopy = new BookCopy();
                    newCopy.setBook(existingBook);
                    newCopy.setStatus("AVAILABLE");
                    bookCopyRepository.save(newCopy);
                }
            }
        }

        Book updatedBook = bookRepository.save(existingBook);
        return Optional.of(updatedBook);
    }

}
