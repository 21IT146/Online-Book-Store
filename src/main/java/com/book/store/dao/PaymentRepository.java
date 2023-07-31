package com.book.store.dao;

import com.book.store.Entities.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment,Integer> {

    public Payment findById(int id);
}
