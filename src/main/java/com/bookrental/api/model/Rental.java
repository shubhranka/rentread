package com.bookrental.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "rentals")
@Data
@AllArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentedOn;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnedOn;

    public Rental() {
    }

    // Getters and Setters
}
