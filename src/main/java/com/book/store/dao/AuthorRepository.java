package com.book.store.dao;

import com.book.store.Entities.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author,Integer>{
    public Author findById(int id);
}
