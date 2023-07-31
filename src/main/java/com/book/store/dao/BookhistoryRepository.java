package com.book.store.dao;

import com.book.store.Entities.Bookhistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookhistoryRepository extends MongoRepository<Bookhistory,Integer> {

    public Bookhistory findById(int id);
}
