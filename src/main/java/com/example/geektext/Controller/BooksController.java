package com.example.geektext.Controller;

import com.example.geektext.Entity.Books;
import com.example.geektext.Service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksController {
    @Autowired
    private BooksService bookService;

    @GetMapping("/books/{genre}")
    public List<Books> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }
}
