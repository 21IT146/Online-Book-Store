package com.book.store.dao;


import com.book.store.Entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student,Integer> {
}
