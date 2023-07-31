package com.book.store.dao;

import com.book.store.Entities.BookPrice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookPriceRepository extends MongoRepository<BookPrice,Integer> {
    public BookPrice findById(int id);
}