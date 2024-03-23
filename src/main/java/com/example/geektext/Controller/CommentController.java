package com.example.geektext.Controller;

import com.example.geektext.Entity.Comment; // Replace with your Comment class package
import com.example.geektext.Repository.CommentRepo; // Replace with your CommentRepository package
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentRepo commentRepo;

    public CommentController(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @PostMapping
    public Comment createComment(
            @RequestParam String commentText,
            @RequestParam Long userId,
            @RequestParam Long bookId) {

        Comment newComment = new Comment(commentText, userId, bookId);
        return commentRepo.save(newComment);
    }
}