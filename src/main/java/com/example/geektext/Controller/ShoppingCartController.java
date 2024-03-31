package com.example.geektext.Controller;

import com.example.geektext.Entity.Book;
import com.example.geektext.Entity.ShoppingCart;
import com.example.geektext.Entity.User;
import com.example.geektext.Repository.BookRepo;
import com.example.geektext.Repository.ShoppingCartRepo;
import com.example.geektext.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @PostMapping("/add/{userId}/{isbn}")
    public ResponseEntity<String> addToCart(@PathVariable String userId, @PathVariable String isbn) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));
        Book book = bookRepo.findById(isbn).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));

        ShoppingCart item = shoppingCartRepo.findByUserAndBook(user, book);
        if (item == null) {
            shoppingCartRepo.save(new ShoppingCart(user, book));
        } else {
            item.incrementQuantity(1);
            shoppingCartRepo.save(item);
        }

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/del/{userId}/{isbn}")
    public ResponseEntity<String> delFromCart(@PathVariable String userId, @PathVariable String isbn) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));
        Book book = bookRepo.findById(isbn).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));

        ShoppingCart item = shoppingCartRepo.findByUserAndBook(user, book);
        if (item == null) {
             throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("No such item in the shopping cart: userId: %s, ISBN: %s ", userId, isbn)
            );
        }

        item.decrementQuantity(1);
        if (item.getQuantity() <= 0) {
            shoppingCartRepo.delete(item);
        } else {
            shoppingCartRepo.save(item);
        }

        return ResponseEntity.ok("");
    }

    @GetMapping("/get/{userId}")
    public List<ShoppingCartResult> listCart(@PathVariable("userId") String userId)
    {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("User not found: %s", userId)
        ));
        List<ShoppingCartResult> results = new ArrayList<>();

        List<ShoppingCart> items = shoppingCartRepo.findByUser(user);
        if (items.isEmpty()) {
            return results;
        }

        for (ShoppingCart item: items) {
            Book book = item.getBook();
            results.add(new ShoppingCartResult(book.getIsbn(), book.getBookName(), book.getPrice(), item.getQuantity()));
        }

        return results;
    }

    @GetMapping("/subtotal/{userId}")
    public double getSubtotal(@PathVariable("userId") String userId)
    {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, String.format("User not found: %s", userId)
        ));
        return shoppingCartRepo.subtotal(user.getUserId());
    }

}

