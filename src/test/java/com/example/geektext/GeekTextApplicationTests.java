package com.example.geektext;
import Entity.User;
import Entity.Wishlist;
import Repository.WishlistRepo;
import Repository.UserRepo;
import Repository.WishlistItemRepo;
import Service.WishlistService;
import org.springframework.data.jpa.repository.JpaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.stereotype.Service;
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
        String userId = "testUserId";
        User user = new User();
        user.setUserID(userId);
        Wishlist wishlist = new Wishlist();
        wishlist.setName("My Wishlist");

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
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
        String newName = "Updated Wishlist";
        Wishlist existingWishlist = new Wishlist();
        existingWishlist.setWishId(wishId);
        existingWishlist.setName("Original Wishlist");

        when(wishlistRepo.findById(wishId)).thenReturn(Optional.of(existingWishlist));
        when(wishlistRepo.save(any(Wishlist.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Wishlist> updatedWishlist = wishlistService.updateWishlist(wishId, newName);

        assertThat(updatedWishlist).isPresent();
        assertThat(updatedWishlist.get().getName()).isEqualTo(newName);
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
