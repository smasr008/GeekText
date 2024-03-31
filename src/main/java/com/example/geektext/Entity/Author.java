package com.example.geektext.Entity;
import jakarta.persistence.*;


@Entity
@Table(name = "authors")
public class Author {

    public Author(String AuthorID, String FirstName, String LastName){
        this.AuthorID = AuthorID;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public Author(){
    }

    @Column(name = "FirstName")
    private String FirstName;

    @Id
    @Column(name = "AuthorID")
    private String AuthorID;

    @Column(name = "LastName")
    private String LastName;

    public String getAuthorID(){
        return this.AuthorID;
    }

    public String getFirstName(){
        return this.FirstName;
    }

    public String getLastName(){
        return this.LastName;
    }
}