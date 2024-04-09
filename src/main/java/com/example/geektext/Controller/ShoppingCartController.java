package com.example.geektext.Controller;

import com.example.geektext.Service.ShoppingCartResult;
import com.example.geektext.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/add/{userId}/{isbn}")
    public ResponseEntity<String> addToCart(@PathVariable String userId, @PathVariable String isbn) {
        try {
            shoppingCartService.addToCart(userId, isbn);
            return ResponseEntity.ok("");
        } catch (NoSuchElementException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getLocalizedMessage());
        }
    }

    @DeleteMapping("/del/{userId}/{isbn}")
    public ResponseEntity<String> delFromCart(@PathVariable String userId, @PathVariable String isbn) {
        try {
            shoppingCartService.delFromCart(userId, isbn);
            return ResponseEntity.ok("");
        } catch (NoSuchElementException error) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, error.getLocalizedMessage());
        }
    }

    @GetMapping("/get/{userId}")
    public List<ShoppingCartResult> listCart(@PathVariable("userId") String userId) {
        try {
            return shoppingCartService.listCart(userId);
        } catch (NoSuchElementException error) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("User not found: %s", userId)
            );
        }
    }

    @GetMapping("/subtotal/{userId}")
    public double getSubtotal(@PathVariable("userId") String userId) {
        try {
            return shoppingCartService.getSubtotal(userId);
        } catch (NoSuchElementException error) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, String.format("User not found: %s", userId)
            );
        }
    }

}

