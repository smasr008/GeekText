package com.example.geektext.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Table(name = "ShoppingCart", uniqueConstraints = {@UniqueConstraint(name = "UniqueCartItem", columnNames = { "UserID", "ISBN" })})
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ISBN", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Book book;

    @Column(name = "Quantity", nullable = false)
    long quantity = 1;

    public ShoppingCart() {
    }

    public ShoppingCart(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity(long quantity) {
        this.quantity += quantity;
    }

    public void decrementQuantity(long quantity) {
        this.quantity -= quantity;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }
}
