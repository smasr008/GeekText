package Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.geektext.GeekTextApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import Repository.UserRepo;
import Repository.WishlistRepo;
import Entity.User;
import Entity.Wishlist;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import Entity.Books;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

    @Nested
    @SpringBootTest (classes = GeekTextApplication.class)
    class WishlistServiceTest {
            @Mock
            private WishlistRepo wishlistRepo;

            @Mock
            private UserRepo userRepo;

            @InjectMocks
            private WishlistService wishlistService;

            @BeforeEach
            void setUp() {
                MockitoAnnotations.openMocks(this);
            }

            @Test
            void testCreateWishlist() {
                String userId = "testUserId";
                User user = new User();
                user.setUserID(userId);
                Wishlist wishlist = new Wishlist();
                wishlist.setName("My Wishlist");

                // Ensure the user is found
                when(userRepo.findById(userId)).thenReturn(Optional.of(user));
                // Mock saving the wishlist
                when(wishlistRepo.save(any(Wishlist.class))).thenReturn(wishlist);

                Wishlist createdWishlist = wishlistService.createWishlist(userId, "My Wishlist");

                assertThat(createdWishlist).isNotNull();
                assertThat(createdWishlist.getName()).isEqualTo("My Wishlist");
                verify(userRepo).findById(userId);
                verify(wishlistRepo).save(any(Wishlist.class));
            }

            @Test
            void testUpdateWishlist() {
                Long wishId = 1L;
                Wishlist existingWishlist = new Wishlist();
                existingWishlist.setWishId(wishId);
                existingWishlist.setName("Original Name");

                // Ensure the wishlist is found
                when(wishlistRepo.findById(wishId)).thenReturn(Optional.of(existingWishlist));
                // Mock saving the updated wishlist
                when(wishlistRepo.save(any(Wishlist.class))).thenAnswer(i -> i.getArguments()[0]);

                Optional<Wishlist> updatedWishlist = wishlistService.updateWishlist(wishId, "Updated Name");

                assertThat(updatedWishlist).isPresent();
                assertThat(updatedWishlist.get().getName()).isEqualTo("Updated Name");
                verify(wishlistRepo).findById(wishId);
                verify(wishlistRepo).save(any(Wishlist.class));
            }

            @Test
            void testDeleteWishlist() {
                Long wishId = 1L;
                doNothing().when(wishlistRepo).deleteById(wishId);

                wishlistService.deleteWishlist(wishId);

                verify(wishlistRepo).deleteById(wishId);
            }
        }
