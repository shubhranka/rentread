package com.bookrental.api.repository;

import com.bookrental.api.model.Book;
import com.bookrental.api.model.Rental;
import com.bookrental.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserAndReturnedOnIsNull(User user);

    Optional<Object> findByUserAndBookAndReturnedOnIsNull(User user, Book book);
}
