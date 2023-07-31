package com.book.store.Services;

import com.book.store.Entities.Book;
import com.book.store.Entities.BookPrice;
import com.book.store.Entities.Order;
import com.book.store.dao.BookPriceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BookPriceService {

    @Autowired
    private BookPriceRepository bookPriceRepository;

    public List<BookPrice> getAllBooks(){
        List<BookPrice> list=(List<BookPrice>)this.bookPriceRepository.findAll();
        return list;
    }

    //get single book by id
    public BookPrice getBookById(int id){
        BookPrice book=null;
        try {
            book=this.bookPriceRepository.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return book;
    }
    //Adding the book
    public BookPrice addBook(BookPrice b){
        BookPrice result=bookPriceRepository.save(b);
        return result;
    }
    //delete book
    public void deleteBook(int bid){
        bookPriceRepository.deleteById(bid);
    }

    //update book
    public void updateBook(BookPrice book, int bookId) {
        book.setId(bookId);
        bookPriceRepository.save(book);
    }
    public String getLatestTime()
    {
        Order d1=new Order();
        Date time=new Date();
        Timestamp ts=new Timestamp(time.getTime());
        return ts.toString();
    }

    public BookPrice getJson(String bookPrice)
    {
        BookPrice bookPriceJson=new BookPrice();
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            bookPriceJson=objectMapper.readValue(bookPrice,BookPrice.class);
        }
        catch(IOException e)
        {
            System.out.printf("Error",e.toString());
        }
        return bookPriceJson;

    }
}
