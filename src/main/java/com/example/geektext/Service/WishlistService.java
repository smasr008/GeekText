package com.example.geektext.Service;

import com.example.geektext.Entity.Wishlist;
import com.example.geektext.Repository.UserRepo;
import com.example.geektext.Repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepo wishlistRepo;
    private final UserRepo userRepo;

    public WishlistService(WishlistRepo wishlistRepo, UserRepo userRepo) {
        this.wishlistRepo = wishlistRepo;
        this.userRepo = userRepo;
    }
    @Transactional
    public Wishlist createWishlist(String UserID, String wishlistName) {
        if (UserID == null || UserID.trim().isEmpty() || wishlistName == null || wishlistName.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID and Wishlist Name must not be empty");
        }

        return userRepo.findById(UserID)
                .map(user -> {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setName(wishlistName);
                    wishlist.setUser(user);
                    return wishlistRepo.save(wishlist);
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + UserID));
    }

    public List<Wishlist> findAllByUserId(String UserID) {
        return wishlistRepo.findAllByUserId(UserID);
    }

    @Transactional
    public Optional<Wishlist> updateWishlist(Long wishID, String newName) {
        return wishlistRepo.findById(wishID)
                .map(wishlist -> {
                    wishlist.setName(newName);
                    return Optional.of(wishlistRepo.save(wishlist));
                })
                .orElse(Optional.empty());
    }

    public void deleteWishlist(Long wishID) {
        wishlistRepo.deleteById(wishID);
    }
}
