package com.book.store.Controllers;

import com.book.store.Entities.Book;
import com.book.store.Entities.BookPrice;
import com.book.store.Entities.Bookhistory;
import com.book.store.Services.BookPriceService;
import com.book.store.dao.BookPriceRepository;
import com.book.store.dao.BookRepository;
import com.book.store.dao.BookhistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookPriceController {

    @Autowired
    private BookPriceRepository bookPriceRepository;

    @Autowired
    private BookPriceService bookPriceService;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookhistoryRepository bookhistoryRepository;
    @PostMapping("/bookPrice")
    public ResponseEntity<BookPrice> addbooks(@RequestBody BookPrice bookPrice){

        //BookPrice b1=bookPriceService.getJson(bookPrice);
        //BookPrice b1=;
        //Book b=this.bookRepository.findById(b.getId());
        try {

            //b1 = this.bookPriceService.addBook(bookPrice);
            bookPrice.setTime(bookPriceService.getLatestTime());
            //b1.setAvgPrice((b.getCurrPrice()+b1.getLastPrice()+ b1.getAvgPrice())/3);
            this.bookPriceRepository.save(bookPrice);
            System.out.println(bookPrice);
            return ResponseEntity.of(Optional.of(bookPrice));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/bookPrice")
    public ResponseEntity<List<BookPrice>> getbook()
    {
        List<BookPrice> list= this.bookPriceService.getAllBooks();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/bookPrice/{id}")
    public ResponseEntity<BookPrice> getbook(@PathVariable("id") int id)
    {

        BookPrice bookPrice =bookPriceService.getBookById(id);

        if(bookPrice ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(bookPrice));
    }
    //delete order handler
    @DeleteMapping("/bookPrice/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
        try {
            this.bookPriceService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update order handler
    @PutMapping("/bookPrice/{id}")
    public ResponseEntity<BookPrice> updateBook(@RequestBody BookPrice bookPrice, @PathVariable("id") int id){

        try {
            this.bookPriceService.updateBook(bookPrice,id);
            return ResponseEntity.ok().body(bookPrice);
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
