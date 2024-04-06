package com.bookrental.api.controller;

import com.bookrental.api.model.Book;
import com.bookrental.api.model.Rental;
import com.bookrental.api.model.User;
import com.bookrental.api.repository.BookRepository;
import com.bookrental.api.repository.RentalRepository;
import com.bookrental.api.repository.UserRepository;
import com.bookrental.api.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/{bookId}/rent")
    public ResponseEntity<String> rentBook(@PathVariable Long bookId, @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(rentalService.rentBook(bookId, userDetails), HttpStatus.OK);
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(rentalService.returnBook(bookId, userDetails), HttpStatus.OK);
    }
}
