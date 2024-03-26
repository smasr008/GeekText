package com.example.geektext.Controller;
import com.example.geektext.Entity.Wishlist;
import com.example.geektext.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{UserID}")
    public ResponseEntity<Wishlist> createWishlist(@PathVariable String UserID, @RequestBody String wishlistName) {
        Wishlist createdWishlist = wishlistService.createWishlist(UserID, wishlistName);
        return ResponseEntity.ok(createdWishlist);
    }



}