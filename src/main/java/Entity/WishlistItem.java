package Entity;

import jakarta.persistence.*;
import Entity.Books;
import java.awt.print.Book;

@Entity
@Table(name = "wishlistitems")
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishListItemId;

    @ManyToOne
    @JoinColumn(name = "WishID")
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "ISBN")
    private Books book; // Ensure this is pointing to your Books entity

    public WishlistItem() {}

    // Getters and Setters
    public Long getWishListItemId() {
        return wishListItemId;
    }

    public void setWishListItemId(Long wishListItemId) {
        this.wishListItemId = wishListItemId;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
}