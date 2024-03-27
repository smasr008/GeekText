package com.example.geektext.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    private String username;
    private String pw;
    private String fullName;
    private String emailAddress;
    private String homeAddress;
    private String creditCardNumber;
    private String creditCardExpirationDate;
    private String creditCardCVV;
    public User() {}
    public User(String username, String password, String fullName, String email,String homeAddress) {
        this.username = username;
        this.pw= password;
        this.emailAddress = email;
        this.fullName = fullName;
        this.homeAddress = homeAddress;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpirationDate = creditCardExpirationDate;
        this.creditCardCVV = creditCardCVV;
    }
    public Long getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = Long.valueOf(userID);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }


}
