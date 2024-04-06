package com.bookrental.api.services;

import com.bookrental.api.model.Rental;
import com.bookrental.api.model.User;
import com.bookrental.api.repository.BookRepository;
import com.bookrental.api.repository.RentalRepository;
import com.bookrental.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public String rentBook(Long bookId, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
        if (user == null) {
            return "User not found.";
        }
        if (rentalRepository.findByUserAndReturnedOnIsNull(user).size() >= 2) {
            return "User already has two active rentals.";
        }
        return bookRepository.findById(bookId).map(book -> {
            if (!book.getAvailabilityStatus()) {
                return "Book is not available for rent.";
            }
            Rental rental = new Rental();
            rental.setUser(user);
            rental.setBook(book);
            rental.setRentedOn(new Date());
            rentalRepository.save(rental);
            book.setAvailabilityStatus(false);
            bookRepository.save(book);
            return "Book rented successfully.";
        }).orElse("Book not found.");
    }

    public String returnBook(Long bookId, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);
        if (user == null) {
            return "User not found.";
        }
        return bookRepository.findById(bookId).map(book -> {
            Rental rental = (Rental) rentalRepository.findByUserAndBookAndReturnedOnIsNull(user,book).orElse(null);
            if (rental == null) {
                return "Rental not found.";
            }
            rental.setReturnedOn(new Date());
            rentalRepository.save(rental);
            book.setAvailabilityStatus(true);
            bookRepository.save(book);
            return "Book returned successfully.";
        }).orElse("Book not found.");
    }



}
