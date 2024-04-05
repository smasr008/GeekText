package com.example.geektext.Repository;

import com.example.geektext.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}

// TODO: Add more features here