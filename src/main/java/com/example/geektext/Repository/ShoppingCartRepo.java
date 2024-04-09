package com.example.geektext.Repository;

import com.example.geektext.Entity.Book;
import com.example.geektext.Entity.ShoppingCart;
import com.example.geektext.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserAndBook(User user, Book book);

    List<ShoppingCart> findByUser(User user);

    @Query("SELECT COALESCE(SUM(sc.quantity * b.price), 0) FROM ShoppingCart sc JOIN sc.book b WHERE sc.user.userID = :userId")
    Double subtotal(@Param("userId") String userId);
}
