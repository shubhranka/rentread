package com.bookrental.api.services;

import com.bookrental.api.model.Book;
import com.bookrental.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book bookDetails) {
        return bookRepository.findById(bookId).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setGenre(bookDetails.getGenre());
            book.setAvailabilityStatus(bookDetails.getAvailabilityStatus());
            return bookRepository.save(book);
        }).orElse(null);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
