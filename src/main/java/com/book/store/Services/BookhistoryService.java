package com.book.store.Services;

import com.book.store.Entities.Author;
import com.book.store.Entities.Book;
import com.book.store.Entities.Bookhistory;
import com.book.store.Entities.Order;
import com.book.store.dao.BookhistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BookhistoryService {

    @Autowired
    private BookhistoryRepository bookhistoryRepository;

    public List<Bookhistory> getAllBooks(){
        List<Bookhistory> list=(List<Bookhistory>)this.bookhistoryRepository.findAll();
        return list;
    }

    //get single book by id
    public Bookhistory getBookById(int id){
        Bookhistory book=null;
        try {
            book=this.bookhistoryRepository.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return book;
    }

    //Adding the book
    public Bookhistory addBook(Bookhistory b){
        Bookhistory result=bookhistoryRepository.save(b);
        return result;
    }
    //delete book
    public void deleteBook(int bid){
        bookhistoryRepository.deleteById(bid);
    }

    //update book
    public void updateBook(Bookhistory bookhistory, int bookId) {
        bookhistory.setId(bookId);
        bookhistoryRepository.save(bookhistory);
    }

    public String getLatestTime()
    {
        Order d1=new Order();
        Date time=new Date();
        Timestamp ts=new Timestamp(time.getTime());
        return ts.toString();
    }
}
