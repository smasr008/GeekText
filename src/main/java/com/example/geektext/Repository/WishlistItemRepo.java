package com.example.geektext.Repository;
import com.example.geektext.Entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistItemRepo extends JpaRepository<WishlistItem, Long> {
}
