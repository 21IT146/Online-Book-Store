package com.book.store.Controllers;

import com.book.store.Entities.Author;
import com.book.store.Entities.Book;
import com.book.store.Entities.BookAuthor;
import com.book.store.Services.BookServices;
import com.book.store.dao.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookAuthorController {

    @Autowired
    private BookServices bookServices;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/bookauthor/{id}")
    public ResponseEntity<BookAuthor> getBook(@PathVariable("id") int id)
    {
        Book book=bookServices.getBookById(id);
        Author u=authorRepository.findById(book.getAuthor_id());

        BookAuthor b=new BookAuthor();
        b.setId(book.getId());
        b.setAuthor_id(book.getAuthor_id());
        b.setTitle(book.getTitle());
        b.setFileUrl(book.getFileUrl());
        b.setName(u.getName());
        b.setEmailid(u.getEmailId());
        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(b));
    }

}
