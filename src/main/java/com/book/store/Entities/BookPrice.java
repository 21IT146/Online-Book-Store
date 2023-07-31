package com.book.store.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookPrice")
public class BookPrice {
    @Id
    private int id;
    private int lastPrice;
    private int avgPrice;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(int lastPrice) {
        this.lastPrice = lastPrice;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BookPrice() {
    }

    public BookPrice(int id, int lastPrice, int avgPrice, String time) {
        this.id = id;
        this.lastPrice = lastPrice;
        this.avgPrice = avgPrice;
        this.time = time;
    }

    @Override
    public String toString() {
        return "BookPrice{" +
                "id=" + id +
                ", lastPrice=" + lastPrice +
                ", avgPrice=" + avgPrice +
                ", time='" + time + '\'' +
                '}';
    }
}
