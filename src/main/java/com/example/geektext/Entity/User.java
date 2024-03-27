package com.example.geektext.Entity;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "UserID", length = 8)
    private String userId;

    @Column(name = "Username", length = 32, unique = true)
    private String userName;

    @Column(name = "PW", length = 32)
    private String password;

    @Column(name = "FullName", length = 32)
    private String fullName;

    @Column(name = "EmailAddress", length = 32)
    private String email;

    @Column(name = "HomeAddress", length = 32)
    private String home;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ShoppingCart> cart;

    public User() {
    }

    public User(String userId, String userName, String password, String fullName, String email, String home) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.home = home;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Set<ShoppingCart> getCart() {
        return cart;
    }
}