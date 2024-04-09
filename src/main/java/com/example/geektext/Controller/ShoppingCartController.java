package com.example.geektext.Controller;
import java.util.Optional;
import java.util.List;
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

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @PostMapping("/add/{userId}/{isbn}")
    public ResponseEntity<?> addToCart(@PathVariable String userId, @PathVariable String isbn) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Book book = bookRepo.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        ShoppingCart existingItem = shoppingCartRepo.findByUserAndBook(user, book).orElse(null);
        if (existingItem == null) {
            ShoppingCart newItem = new ShoppingCart(user, book);
            shoppingCartRepo.save(newItem);
            return ResponseEntity.ok(newItem);
        } else {
            existingItem.incrementQuantity(1);
            shoppingCartRepo.save(existingItem);
            return ResponseEntity.ok(existingItem);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> listCart(@PathVariable String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<ShoppingCart> cartItems = shoppingCartRepo.findByUser(user);
        if (cartItems.isEmpty()) {
            return ResponseEntity.ok("Shopping cart is empty");
        }
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long cartItemId) {
        ShoppingCart item = shoppingCartRepo.findById(cartItemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));

        if (item.getQuantity() > 1) {
            item.decrementQuantity(1);
            shoppingCartRepo.save(item);
        } else {
            shoppingCartRepo.delete(item);
        }
        return ResponseEntity.ok().build();
    }
}
