package com.example.geektext.Controller;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.example.geektext.Entity.Wishlist;
import com.example.geektext.Entity.WishlistItem;
import com.example.geektext.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{userID}")
    public ResponseEntity<Wishlist> createWishlist(@PathVariable String userID, @RequestBody String wishlistName) {
        Wishlist createdWishlist = wishlistService.createWishlist(userID, wishlistName);
        return ResponseEntity.ok(createdWishlist);
    }

    @GetMapping("/{wishId}")
    public ResponseEntity<Map<String, Object>> getWishlistFiltered(@PathVariable Long wishId) {
        return wishlistService.findWishlistById(wishId)
                .map(wishlist -> {
                    List<Map<String, Object>> books = wishlist.getItems().stream()
                            .map(item -> {
                                Map<String, Object> bookDetails = new HashMap<>();
                                bookDetails.put("isbn", item.getBook().getISBN());
                                bookDetails.put("bookName", item.getBook().getBookName());
                                bookDetails.put("genre", item.getBook().getGenre());
                                bookDetails.put("authorID", item.getBook().getAuthorID());
                                return bookDetails;
                            })
                            .collect(Collectors.toList());

                    Map<String, Object> response = new HashMap<>();
                    response.put("name", wishlist.getName());
                    response.put("items", books);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{wishId}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long wishId, @RequestBody String newName) {
        return wishlistService.updateWishlist(wishId, newName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{wishId}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long wishId) {
        wishlistService.deleteWishlist(wishId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addBook/{wishlistId}/{bookISBN}")
    public ResponseEntity<WishlistItem> addBookToWishlist(@PathVariable Long wishlistId, @PathVariable String bookISBN) {
        WishlistItem wishlistItem = wishlistService.addBookToWishlist(wishlistId, bookISBN);
        return ResponseEntity.ok(wishlistItem);
    }

    @DeleteMapping("/removeBook/{wishlistItemId}")
    public ResponseEntity<Void> removeBookFromWishlist(@PathVariable Long wishlistItemId) {
        wishlistService.removeBookFromWishlist(wishlistItemId);
        return ResponseEntity.ok().build();
    }
}
