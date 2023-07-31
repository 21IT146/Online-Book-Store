package com.book.store.Entities;

import org.springframework.data.annotation.Id;

public class BookAuthor {

    @Id
    private int id;
    private String title;
    private String fileUrl;

    private int author_id;

    private String name;
    private String role;
    private String emailid;

    public BookAuthor() {

    }

    public BookAuthor(int id, String title, String fileUrl, int author_id, String name, String role, String emailid) {
        this.id = id;
        this.title = title;
        this.fileUrl = fileUrl;
        this.author_id = author_id;
        this.name = name;
        this.role = role;
        this.emailid = emailid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", author_id=" + author_id +
                ", name='" + name + '\'' +
                ", emailid='" + emailid + '\'' +
                '}';
    }
}
