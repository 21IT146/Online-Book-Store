package com.book.store.dao;

import com.book.store.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,Integer> {

    public User findById(int id);

    User findByNameLike(String name);
}
