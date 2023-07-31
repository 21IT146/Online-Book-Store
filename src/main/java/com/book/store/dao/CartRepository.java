package com.book.store.dao;


import com.book.store.Entities.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, Integer> {
}
