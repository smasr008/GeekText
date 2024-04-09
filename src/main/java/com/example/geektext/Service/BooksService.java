package com.example.geektext.Service;

import com.example.geektext.Entity.Book;
import com.example.geektext.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final BookRepo bookRepo;

    @Autowired
    public BooksService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookRepo.findByGenre(genre);
    }
}
