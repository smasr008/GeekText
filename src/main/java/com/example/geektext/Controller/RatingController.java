package com.example.geektext.Controller;

import com.example.geektext.Repository.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public void createRating(
            @RequestParam int rating,
            @RequestParam Long userId,
            @RequestParam String bookId) {
        ratingService.saveRating(rating, userId, bookId);
        // Response is void, as required
    }
}
