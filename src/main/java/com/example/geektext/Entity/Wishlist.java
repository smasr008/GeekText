package com.example.geektext.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "wishlists")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "wishID")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishID;

    private String name; // Field for the name of the wishlist

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private User user;

    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WishlistItem> items = new HashSet<>();

    public Wishlist() {}

    // Getters and Setters
    public Long getWishID() {
        return wishID;
    }

    public void setWishID(Long wishID) {
        this.wishID = wishID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
