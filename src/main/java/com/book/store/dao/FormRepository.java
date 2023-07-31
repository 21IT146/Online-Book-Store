package com.book.store.dao;

import com.book.store.Entities.Form;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormRepository extends MongoRepository<Form,String> {
    //Form findFirstByOrderByPassword();
    Boolean existsByUsername(String username);
    Form findByUsername(String username);
    Boolean existsByPassword(String password);
}
