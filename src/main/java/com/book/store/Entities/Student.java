package com.book.store.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="student")
public class Student {
    private int Id;
    private String Name;
    private String City;
    private String College;

    public Student(int id, String name, String city, String college) {
        Id = id;
        Name = name;
        City = city;
        College = college;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }
}
