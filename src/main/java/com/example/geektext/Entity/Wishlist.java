package com.example.geektext.Entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wishlists")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long WishID;

    private String name; // Added field for the name of the wishlist

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User user;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WishlistItem> items = new HashSet<>();

    public Wishlist() {}

    // Getters and Setters
    public Long getWishId() {
        return WishID;
    }

    public void setWishId(Long wishId) {
        this.WishID = wishId;
    }

    public String getName() { // Getter for name
        return name;
    }

    public void setName(String name) { // Setter for name
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<WishlistItem> getItems() {
        return items;
    }

    public void setItems(Set<WishlistItem> items) {
        this.items = items;
    }
}