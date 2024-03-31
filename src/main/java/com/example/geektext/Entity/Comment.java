package com.example.geektext.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentText;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime datestamp;

    @Column(nullable = false)
    private Long bookId;

    // Default constructor for JPA
    public Comment() {}

    // Custom constructor for creating a Comment object
    public Comment(String commentText, Long userId, Long bookId) {
        this.commentText = commentText;
        this.userId = userId;
        this.bookId = bookId;
        this.datestamp = LocalDateTime.now();
    }
}