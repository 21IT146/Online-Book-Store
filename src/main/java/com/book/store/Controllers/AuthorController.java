package com.book.store.Controllers;


import com.book.store.Entities.Author;
import com.book.store.Services.AuthorServices;
import com.book.store.dao.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorServices authorServices;

//    public ResponseEntity<?> addAuthor(@RequestBody Author author)
//    {
//        Author save=this.authorRepository.save(author);
//        return ResponseEntity.ok(save);
//    }

    @PostMapping("/author")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){

        Author b1=null;
        try {
            b1 = this.authorServices.addauthor(author);
            System.out.println(author);
            return ResponseEntity.of(Optional.of(b1));
             //return ResponseEntity.status(HttpStatus.CREATED).build();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
//    @GetMapping("/author")
//    public ResponseEntity<?> getauthor()
//    {
//        return ResponseEntity.ok(this.authorRepository.findAll());
//    }
    @GetMapping("/author")
    public ResponseEntity<List<Author>> getauthor()//@RequestHeader(value="accessKey") String accessKey in getBook()
    {
        List<Author> list= this.authorServices.getAllAuthors();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getauthor(@PathVariable("id") int id)
    {

        Author author =authorServices.getAuthorById(id);

        if(author ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(author));


    }

}
