package com.book.store.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "payment")
public class Payment {
    @Id
    private int id;
    private String time;
    private int Order_Id;
    private int User_Id;

    public Payment() {
    }

    public Payment(int id, String time, int order_Id, int user_Id) {
        this.id = id;
        this.time = time;
        Order_Id = order_Id;
        User_Id = user_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = order_Id;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", Order_Id=" + Order_Id +
                ", User_Id=" + User_Id +
                '}';
    }
}
