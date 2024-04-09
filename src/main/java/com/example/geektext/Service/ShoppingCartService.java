package com.example.geektext.Service;

import com.example.geektext.Entity.Book;
import com.example.geektext.Entity.ShoppingCart;
import com.example.geektext.Entity.User;
import com.example.geektext.Repository.BookRepo;
import com.example.geektext.Repository.ShoppingCartRepo;
import com.example.geektext.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShoppingCartService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Transactional
    public void addToCart(String userId, String isbn) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException(
                String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));
        Book book = bookRepo.findById(isbn).orElseThrow(() -> new NoSuchElementException(
                String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));

        ShoppingCart item = shoppingCartRepo.findByUserAndBook(user, book);
        if (item == null) {
            shoppingCartRepo.save(new ShoppingCart(user, book));
        } else {
            item.incrementQuantity(1);
            shoppingCartRepo.save(item);
        }
    }

    @Transactional
    public void delFromCart(String userId, String isbn) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException(
                String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));
        Book book = bookRepo.findById(isbn).orElseThrow(() -> new NoSuchElementException(
                String.format("Either user: %s or book with ISBN: %s not found", userId, isbn)
        ));

        ShoppingCart item = shoppingCartRepo.findByUserAndBook(user, book);
        if (item == null) {
            throw new NoSuchElementException(
                    String.format("No such item in the shopping cart: userId: %s, ISBN: %s ", userId, isbn)
            );
        }

        item.decrementQuantity(1);
        if (item.getQuantity() <= 0) {
            shoppingCartRepo.delete(item);
        } else {
            shoppingCartRepo.save(item);
        }
    }

    @Transactional
    public List<ShoppingCartResult> listCart(String userId) {
        User user = userRepo.findById(userId).orElseThrow(NoSuchElementException::new);
        List<ShoppingCartResult> results = new ArrayList<>();
        List<ShoppingCart> items = shoppingCartRepo.findByUser(user);
        if (items.isEmpty()) {
            return results;
        }

        for (ShoppingCart item : items) {
            Book book = item.getBook();
            results.add(new ShoppingCartResult(book.getIsbn(), book.getBookName(), book.getPrice(), item.getQuantity()));
        }

        return results;
    }

    @Transactional
    public double getSubtotal(String userId) {
        User user = userRepo.findById(userId).orElseThrow(NoSuchElementException::new);
        return shoppingCartRepo.subtotal(user.getUserId());
    }
}
