package com.example.geektext.Service;
import com.example.geektext.Entity.User;
import com.example.geektext.Entity.Books;
import com.example.geektext.Entity.Wishlist;
import com.example.geektext.Entity.WishlistItem;
import com.example.geektext.Repository.BookRepo;
import com.example.geektext.Repository.UserRepo;
import com.example.geektext.Repository.WishlistItemRepo;
import com.example.geektext.Repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private WishlistItemRepo wishlistItemRepo;

    @Transactional
    public Wishlist createWishlist(String userID, String wishlistName) {
        return userRepo.findById(userID)
                .map(user -> {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setName(wishlistName);
                    wishlist.setUser(user);
                    return wishlistRepo.save(wishlist);
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userID));
    }

    public List<Wishlist> findAllByUserId(String userID) {
        return wishlistRepo.findAllByUserId(userID);
    }
    public Optional<Wishlist> findWishlistById(Long wishId) {
        return wishlistRepo.findById(wishId);
    }

    @Transactional
    public Optional<Wishlist> updateWishlist(Long wishID, String newName) {
        return wishlistRepo.findById(wishID)
                .map(wishlist -> {
                    wishlist.setName(newName);
                    return wishlistRepo.save(wishlist);
                });
    }

    public void deleteWishlist(Long wishID) {
        wishlistRepo.deleteById(wishID);
    }

    @Transactional
    public WishlistItem addBookToWishlist(Long wishlistId, String bookISBN) {
        Wishlist wishlist = wishlistRepo.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found with ID: " + wishlistId));
        Books book = bookRepo.findByISBN(bookISBN)
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + bookISBN));

        WishlistItem wishlistItem = new WishlistItem();
        wishlistItem.setWishlist(wishlist);
        wishlistItem.setBook(book);
        return wishlistItemRepo.save(wishlistItem);
    }

    @Transactional
    public void removeBookFromWishlist(Long wishlistItemId) {
        if (!wishlistItemRepo.existsById(wishlistItemId)) {
            throw new RuntimeException("WishlistItem not found with ID: " + wishlistItemId);
        }
        wishlistItemRepo.deleteById(wishlistItemId);
    }
}
