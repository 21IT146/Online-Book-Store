package com.book.store.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection ="bookRating")
public class BookRating {

    @Id
    private int id;
    private int bookId;
    private String rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public BookRating() {
    }

    @Override
    public String toString() {
        return "BookRating{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", rating='" + rating + '\'' +
                '}';
    }

    public BookRating(int id, int bookId, String rating) {
        this.id = id;
        this.bookId = bookId;
        this.rating = rating;
    }
}
