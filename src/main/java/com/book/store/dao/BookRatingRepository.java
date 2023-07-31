package com.book.store.dao;

import com.book.store.Entities.BookRating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRatingRepository extends MongoRepository<BookRating,Integer> {

    public BookRating findById(int id);
}
