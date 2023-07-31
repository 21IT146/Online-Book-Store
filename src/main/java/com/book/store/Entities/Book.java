package com.book.store.Entities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
public class Book {

    @Id
    private int id;
    private String title;

    private String fileUrl;
    private int author_id;

    private Integer currPrice;
    public Book(){

    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    public int getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(int currPrice) {
        this.currPrice = currPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", author_id=" + author_id +
                ", currPrice=" + currPrice +
                '}';
    }

    public Book(int id, String title, String fileUrl, int author_id, int currPrice) {
        this.id = id;
        this.title = title;
        this.fileUrl = fileUrl;
        this.author_id = author_id;
        this.currPrice = currPrice;
    }

    public Book(String book) {
        Book b=new Book();
        try{
             b=new ObjectMapper().readValue(book,Book.class);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        this.id = b.getId();
        this.title = b.getTitle();
        this.fileUrl = b.getFileUrl();
        this.author_id=b.getAuthor_id();
        this.currPrice=b.getCurrPrice();
    }
}
