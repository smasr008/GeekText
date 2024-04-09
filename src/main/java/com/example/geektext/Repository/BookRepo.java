package com.example.geektext.Repository;

import com.example.geektext.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, String> {
    List<Book> findByGenre(String genre);
    Optional<Book> findByIsbn(String isbn);
}
