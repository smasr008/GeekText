package com.example.geektext.Repository;
import com.example.geektext.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo<Rating> extends JpaRepository<Rating, Long> {
    default List<Rating> findByBookId(String bookId) {
        return null;
    }
}

// Add more features here