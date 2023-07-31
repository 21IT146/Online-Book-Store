package com.book.store.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart")
public class Cart {
    private int Book_Id;
    private int User_Id;

    public Cart() {
    }

    public Cart(int book_Id, int user_Id) {
        Book_Id = book_Id;
        User_Id = user_Id;
    }

    public int getBook_Id() {
        return Book_Id;
    }

    public void setBook_Id(int book_Id) {
        Book_Id = book_Id;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "Book_Id=" + Book_Id +
                ", User_Id=" + User_Id +
                '}';
    }
}
