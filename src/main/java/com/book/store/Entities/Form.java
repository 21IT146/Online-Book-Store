package com.book.store.Entities;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("form")
public class Form {

    private String username;

    public String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Form(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Form()
    {

    }


    @Override
    public String toString() {
        return "form{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
