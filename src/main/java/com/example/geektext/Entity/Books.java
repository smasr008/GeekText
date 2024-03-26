package com.example.geektext.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @Column(name = "ISBN")
    private String ISBN;

    @Column(name = "BookName")
    private String BookName;

    @Column(name = "AuthorID")
    private String AuthorID;

    // Constructors, getters, and setters

    public Books() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        this.BookName = bookName;
    }

    public String getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(String authorID) {
        this.AuthorID = authorID;
    }
}
