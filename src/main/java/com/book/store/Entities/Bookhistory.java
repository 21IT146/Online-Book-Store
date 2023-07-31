package com.book.store.Entities;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "bookhistory")
public class Bookhistory {
    private int id;
    private List<PriceHistory> pricehistory;

    private int avgPrice;

    public List<PriceHistory> getPricehistory() {
        return pricehistory;
    }

    public void setPricehistory(List<PriceHistory> pricehistory) {
        this.pricehistory = pricehistory;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bookhistory() {
    }

    public Bookhistory(int id, List<PriceHistory> pricehistory, int avgPrice) {
        this.id = id;
        this.pricehistory = pricehistory;
        this.avgPrice = avgPrice;
    }

    @Override
    public String toString() {
        return "Bookhistory{" +
                "id=" + id +
                ", pricehistory=" + pricehistory +
                ", avgPrice=" + avgPrice +
                '}';
    }
}
