package com.example.geektext.Repository;

import com.example.geektext.Entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface WishlistRepo extends JpaRepository<Wishlist, Long> { @Query(value = "SELECT * FROM wishlists w INNER JOIN users u ON w.UserID = u.UserID WHERE u.UserID = :userID", nativeQuery = true)
    List<Wishlist> findAllByUserId(String userID);
}
