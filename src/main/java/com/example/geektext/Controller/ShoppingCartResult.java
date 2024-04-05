package com.example.geektext.Controller;


public class ShoppingCartResult {

    private String isbn;

    private String bookName;

    private double price;

    private long quantity;

    public ShoppingCartResult(String isbn, String bookName, double price, long quantity) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.price = price;
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
