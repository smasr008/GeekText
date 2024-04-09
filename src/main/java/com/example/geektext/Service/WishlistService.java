package com.example.geektext.Service;

import com.example.geektext.Entity.Book;
import com.example.geektext.Entity.User;
import com.example.geektext.Entity.Wishlist;
import com.example.geektext.Entity.WishlistItem;
import com.example.geektext.Repository.BookRepo;
import com.example.geektext.Repository.ShoppingCartRepo;
import com.example.geektext.Repository.UserRepo;
import com.example.geektext.Repository.WishlistItemRepo;
import com.example.geektext.Repository.WishlistRepo;
import com.example.geektext.Entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Transactional
    public Wishlist createWishlist(String userID, String wishlistName) {
        User user = userRepo.findById(userID).orElseThrow(() -> new RuntimeException("User not found with ID: " + userID));
        Wishlist wishlist = new Wishlist();
        wishlist.setName(wishlistName);
        wishlist.setUser(user);
        return wishlistRepo.save(wishlist);
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
    public WishlistItem addBookToWishlist(Long wishlistId, String isbn) {
        Wishlist wishlist = wishlistRepo.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found with ID: " + wishlistId));
        Book book = bookRepo.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));

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

    @Transactional
    public void transferBookFromWishlistToCart(Long wishlistItemId, String userId) {
        WishlistItem wishlistItem = wishlistItemRepo.findById(wishlistItemId)
                .orElseThrow(() -> new IllegalStateException("Wishlist item not found"));

        // Ensuring the wishlist item belongs to the user
        if (!wishlistItem.getWishlist().getUser().getUserID().equals(userId)) {
            throw new IllegalStateException("Wishlist item does not belong to the user");
        }

        // Check if the book is already in the cart
        ShoppingCart existingCartItem = shoppingCartRepo.findByUserAndBook(wishlistItem.getWishlist().getUser(), wishlistItem.getBook())
                .orElse(null);

        if (existingCartItem == null) {
            ShoppingCart newItem = new ShoppingCart(wishlistItem.getWishlist().getUser(), wishlistItem.getBook());
            shoppingCartRepo.save(newItem);
        } else {
            existingCartItem.incrementQuantity(1);
            shoppingCartRepo.save(existingCartItem);
        }

        // Remove the item from the wishlist
        wishlistItemRepo.delete(wishlistItem);
    }
}
