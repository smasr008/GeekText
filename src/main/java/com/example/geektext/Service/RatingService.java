package com.example.geektext.Service;

import com.example.geektext.Entity.Rating;
import com.example.geektext.Repository.RatingRepo;
import com.example.geektext.Repository.BookRepo;
import com.example.geektext.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepo ratingRepo;
    private final BookRepo bookRepo;
    private final UserRepo userRepo;

    @Autowired
    public RatingService(RatingRepo ratingRepo, BookRepo bookRepo, UserRepo userRepo) {
        this.ratingRepo = ratingRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public void saveRating(int ratingValue, Long userId, String bookId) {
        Rating rating = new Rating();
        // Set the properties of the rating
        rating.setRating(ratingValue);
        // Lookup User and Book entities by ID
        rating.setUser(userRepo.findById(userId).orElse(null));
        rating.setBook(bookRepo.findById(bookId).orElse(null));
        // Save the rating
        ratingRepo.save(rating);
    }
}
