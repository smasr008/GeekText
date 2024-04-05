package com.example.geektext.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "rating")
    private int rating; // Changed 'Rating' to 'rating' to follow naming conventions

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Ensure this matches your User entity's ID
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "isbn") // Ensure this matches your Book entity's ID
    private Book book;

    // Lombok will handle constructors, getters, and setters
}
