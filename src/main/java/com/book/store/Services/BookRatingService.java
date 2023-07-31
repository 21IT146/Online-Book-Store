package com.book.store.Services;

import com.book.store.Entities.BookRating;
import com.book.store.dao.BookRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRatingService {


    @Autowired
    private BookRatingRepository bookRatingRepository;

    public List<BookRating> getAllBookRating()
    {
        List<BookRating> list=this.bookRatingRepository.findAll();
        return list;
    }
    public BookRating addbookRating(BookRating bookRating)
    {
        BookRating result=bookRatingRepository.save(bookRating);
        return result;
    }

    public void updateBookRating(BookRating bookRating, int id) {

        bookRating.setId(id);
        bookRatingRepository.save(bookRating);
    }

    public BookRating getBookRatingById(int id){
        BookRating bookRating=null;
        try {

            bookRating=this.bookRatingRepository.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return bookRating;
    }
}
