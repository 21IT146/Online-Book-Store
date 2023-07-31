package com.book.store.dao;

import com.book.store.Entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,Integer>{

    public Book findById(int id);

    Book findByTitleLike(String title);

}



