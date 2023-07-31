package com.book.store.dao;

import com.book.store.Entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,Integer> {

    public Order findById(int id);


    public List<Order> findByBookId(int bookId);

}
