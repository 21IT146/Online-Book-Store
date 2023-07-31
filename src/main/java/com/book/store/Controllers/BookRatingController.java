package com.book.store.Controllers;


import com.book.store.Entities.Book;
import com.book.store.Entities.BookRating;
import com.book.store.Entities.Order;
import com.book.store.Services.BookRatingService;
import com.book.store.dao.BookRatingRepository;
import com.book.store.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
public class BookRatingController {

    @Autowired
    private BookRatingRepository bookRatingRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookRatingService bookRatingService;
    @PostMapping("/bookRating")
    public ResponseEntity<BookRating> addBookRating(@RequestParam("id") int id,@RequestParam("bookId") int bookId){

        BookRating b1=new BookRating();
        List<Order> books =this.orderRepository.findByBookId(bookId);
        Order o=new Order();
        System.out.println(books);


        float totalRating = 0;
        int validBooks = 0;
        for (Order book : books){
            try{
                totalRating += Float.parseFloat(book.getRating(book.getBookId().indexOf(bookId)));
                if(Float.parseFloat(book.getRating(book.getBookId().indexOf(bookId)))>0) {
                    validBooks += 1;
                }
            }catch (IndexOutOfBoundsException e){
                Logger.getLogger("TEST LOGGER").info("There was no rating for this order");
            }

        }
        if(validBooks ==0){

        }
        float avgRating = totalRating/validBooks;
        System.out.println(avgRating);
        b1.setId(id);
        b1.setBookId(bookId);
        b1.setRating(Float.toString(avgRating));
        this.bookRatingRepository.save(b1);
//        try {
//            b1 = this.bookRatingService.addbookRating(bookRating);
//            this.bookRatingRepository.save(b1);
//            System.out.println(bookRating);
//            return ResponseEntity.of(Optional.of(b1));
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
        return ResponseEntity.of(Optional.of(b1));
    }
    @GetMapping("/bookRating")
    public ResponseEntity<List<BookRating>> getbookRating()
    {
        List<BookRating> list= this.bookRatingService.getAllBookRating();
        if(list.size()<1)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @PutMapping("/bookRating/{id}")
    public ResponseEntity<BookRating> updateBookRating(@RequestBody BookRating bookRating, @PathVariable("id") int id){
        try {
            this.bookRatingService.updateBookRating(bookRating,id);
            return ResponseEntity.ok().body(bookRating);
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping("/bookRating/{id}")
    public ResponseEntity<BookRating> getBookRating(@PathVariable("id") int id)
    {
        BookRating bookRating =bookRatingService.getBookRatingById(id);

        if(bookRating ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(bookRating));
    }
}
