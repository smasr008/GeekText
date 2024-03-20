package Controller;
import Entity.Wishlist;
import Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{userID}")
    public ResponseEntity<Wishlist> createWishlist(@PathVariable String userId, @RequestBody Wishlist wishlist) {
        Wishlist createdWishlist = wishlistService.createWishlist(userId, wishlist.getName());
        return ResponseEntity.ok(createdWishlist);
    }

    @GetMapping("/{userID}")
    public List<Wishlist> getWishlistsByUserId(@PathVariable String userID) {
        return wishlistService.findAllByUserId(userID);
    }
}