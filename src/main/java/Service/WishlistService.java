package Service;
import Entity.Wishlist;
import Entity.WishlistItem;
import Entity.Books;
import Entity.User;
import Repository.UserRepo;
import Repository.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Service
    public class WishlistService {

    private final WishlistRepo wishlistRepo;
    private final UserRepo userRepo;

    @Autowired
    public WishlistService(WishlistRepo wishlistRepo, UserRepo userRepo) {
        this.wishlistRepo = wishlistRepo;
        this.userRepo = userRepo;
    }

    public Wishlist createWishlist(String userId, String wishlistName) throws RuntimeException {
        Optional<User> user = userRepo.findById(userId); // Use the instance of UserRepo
        if (user.isPresent()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setName(wishlistName);
            wishlist.setUser(user.get());
            return wishlistRepo.save(wishlist); // Use the instance of WishlistRepo
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public List<Wishlist> findAllByUserId(String userId) {
        return wishlistRepo.findAllByUserId(userId);
    }

    public Optional<Wishlist> updateWishlist(Long wishId, String newName) {
        Optional<Wishlist> wishlist = wishlistRepo.findById(wishId);
        if (wishlist.isPresent()) {
            Wishlist updatedWishlist = wishlist.get();
            updatedWishlist.setName(newName);
            wishlistRepo.save(updatedWishlist);
            return Optional.of(updatedWishlist);
        }
        return Optional.empty();
    }

    public void deleteWishlist(Long wishId) {
        wishlistRepo.deleteById(wishId);
    }
}