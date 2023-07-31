package com.book.store.Services;

import com.book.store.Entities.Author;
import com.book.store.dao.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorServices {
    @Autowired
    private AuthorRepository authorRepository;


    public List<Author> getAllAuthors()
    {
        List<Author> list=(List<Author>)this.authorRepository.findAll();
        return list;
    }
    public Author getAuthorById(int id){
        Author author=null;
        try {

            author=this.authorRepository.findById(id);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return author;
    }

    //Adding the Author
    public Author addauthor(Author b){
        Author result=authorRepository.save(b);
        return result;
    }
}
