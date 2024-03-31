package com.example.geektext.Repository;

import com.example.geektext.Entity.Book;
import com.example.geektext.Entity.ShoppingCart;
import com.example.geektext.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByUserAndBook(User user, Book book);

    List<ShoppingCart> findByUser(User user);

    @Query(
        value = "SELECT SUM(Books.Price * ShoppingCart.Quantity) " +
                "FROM ShoppingCart INNER JOIN Books " +
                "ON  ShoppingCart.ISBN = Books.ISBN " +
                "WHERE ShoppingCart.UserID = :userId",
        nativeQuery = true
    )
    double subtotal(@Param("userId") String userId);
}
