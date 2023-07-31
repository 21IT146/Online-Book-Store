package com.book.store.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "order")
public class Order {
    @Id
    private int id;
    private List<Integer> bookId;
    private int userId;
    private String amount;
    private String status;

    private String time;
    public List<String> rating;

    public Order() {
    }

    public Order(int id, List<Integer> bookId, int userId, String amount, String status, String time, List<String> rating) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
        this.time = time;
        this.rating = rating;
    }

    public String getRating(int index) {

            return rating.get(index);

    }

    public void setRating(List<String> rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getBookId() {
        return bookId;
    }

    public void setBookId(List<Integer> bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}