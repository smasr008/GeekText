package com.example.geektext;

import com.example.geektext.Entity.User;
import com.example.geektext.Entity.Wishlist;
import com.example.geektext.Repository.UserRepo;
import com.example.geektext.Repository.WishlistRepo;
import com.example.geektext.Service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class GeekTextApplicationTests {

    @Autowired
    private WishlistService wishlistService;

    @MockBean
    private WishlistRepo wishlistRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    void testCreateWishlist() {
        String userID = "testUserId";
        User user = new User();
        user.setUserID(userID); // Correct method to set user ID
        Wishlist wishlist = new Wishlist();
        wishlist.setName("My Wishlist");

        when(userRepo.findById(userID)).thenReturn(Optional.of(user));
        when(wishlistRepo.save(any(Wishlist.class))).thenReturn(wishlist);

        Wishlist createdWishlist = wishlistService.createWishlist(userID, "My Wishlist");

        assertThat(createdWishlist).isNotNull();
        assertThat(createdWishlist.getName()).isEqualTo("My Wishlist");
        verify(userRepo).findById(userID);
        verify(wishlistRepo).save(any(Wishlist.class));
    }

    @Test
    void testUpdateWishlist() {
        Long wishID = 1L;
        String newName = "Updated Wishlist";
        Wishlist existingWishlist = new Wishlist();
        existingWishlist.setWishId(wishID); // Correct method to set wishlist ID
        existingWishlist.setName("Original Wishlist");

        when(wishlistRepo.findById(wishID)).thenReturn(Optional.of(existingWishlist));
        when(wishlistRepo.save(any(Wishlist.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Wishlist> updatedWishlist = wishlistService.updateWishlist(wishID, newName);

        assertThat(updatedWishlist).isPresent();
        assertThat(updatedWishlist.get().getName()).isEqualTo(newName);
        verify(wishlistRepo).findById(wishID);
        verify(wishlistRepo).save(any(Wishlist.class));
    }

    @Test
    void testDeleteWishlist() {
        Long wishID = 1L;
        doNothing().when(wishlistRepo).deleteById(wishID);

        wishlistService.deleteWishlist(wishID);

        verify(wishlistRepo).deleteById(wishID);
    }
}
