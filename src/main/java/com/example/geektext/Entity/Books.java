package com.example.geektext.Entity;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "Books")
public class Book {

    @Id
    @Column(name = "ISBN", length = 13)
    private String isbn;

    @Column(name = "BookName", length = 64)
    private String bookName;

    @Column(name = "BookDesc", length = 500)
    private String BookDesc;

    @Column(name = "Price")
    private double price;

    @Column(name = "Genre", length = 16)
    private String genre;

    @Column(name = "Publisher", length = 32)
    private String publisher;

    @Column(name = "YearPublished")
    private int yearPublished;

    @Column(name = "CopiesSold")
    private int copiesSold;

    @ManyToOne(optional = false)
    @JoinColumn(name = "AuthorID", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ShoppingCart> cart;

    public Book() {
    }

    public Book(String isbn, String bookName, String bookDesc, double price, String genre, String publisher, int yearPublished, int copiesSold, Author author) {
        this.isbn = isbn;
        this.bookName = bookName;
        BookDesc = bookDesc;
        this.price = price;
        this.genre = genre;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.copiesSold = copiesSold;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDesc() {
        return BookDesc;
    }

    public void setBookDesc(String bookDesc) {
        BookDesc = bookDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getCopiesSold() {
        return copiesSold;
    }

    public void setCopiesSold(int copiesSold) {
        this.copiesSold = copiesSold;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
