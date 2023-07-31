package com.book.store.Controllers;

import com.book.store.Entities.Author;
import com.book.store.Entities.Book;
import com.book.store.Entities.Bookhistory;
import com.book.store.Services.BookhistoryService;
import com.book.store.dao.BookhistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookhistoryController {

    @Autowired
    private BookhistoryRepository bookhistoryRepository;
    @Autowired
    private BookhistoryService bookhistoryService;

    @PostMapping("/bookhistory")
    public ResponseEntity<Bookhistory> addBookhistory(@RequestBody Bookhistory bookhistory){

        Bookhistory b1=null;
        try {
            b1 = this.bookhistoryService.addBook(bookhistory);
            System.out.println(bookhistory);
            return ResponseEntity.of(Optional.of(b1));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
