package com.example.geektext.Service;

import com.example.geektext.Entity.Books;
import com.example.geektext.Entity.Rating;
import com.example.geektext.Repository.BookRepo;
import com.example.geektext.Repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private RatingRepo ratingRepo;

    public List<Books> getBooksByGenre(String genre) {
        return bookRepo.findByGenre(genre);
    }

    public double getAverageRating(String bookId) {
        List<Rating> ratings = ratingRepo.findByBookId(bookId);
        return ratings.stream().mapToDouble(rating -> rating.getRating()).average().orElse(0);
    }
}