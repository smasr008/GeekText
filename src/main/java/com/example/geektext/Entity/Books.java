package com.example.geektext.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Setter
@Entity
@Table(name = "Books")
public class Books {

    public Books(String bookName, String genre) {
        this.bookName = bookName;
        this.genre = genre;
    }

    public Books() {}

    @Getter
    @Column(name = "BookName")
    private String bookName;

    @Getter
    @Column(name = "BookDesc")
    private String bookDesc;

    @Getter
    @Column(name = "Price")
    private double price;

    @Getter
    @Column(name = "Genre")
    private String genre;

    @Getter
    @Column(name = "Publisher")
    private String publisher;

    @Getter
    @Column(name = "CopiesSold")
    private int copiesSold;

    @Getter
    @Column(name = "YearPublished")
    private int yearPublished;

    @Getter
    @Id
    @Column(name = "ISBN")
    private String ISBN;

    @ManyToOne
    @JoinColumn(name = "AuthorID")
    private Author author;

}
