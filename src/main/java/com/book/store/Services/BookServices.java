package com.book.store.Services;

import com.book.store.Entities.Author;
import com.book.store.Entities.Book;
import com.book.store.dao.AuthorRepository;
import com.book.store.dao.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
public class BookServices {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    //private static List<Book> list=new ArrayList<>();
//    static{
//        list.add(new Book(12,"Spring References","Nasir"));
//        list.add(new Book(35,"Boot References","Sakir"));
//        list.add(new Book(23,"java References","Sherasiya"));
//    }
    //get all books
    public List<Book> getAllBooks(){
        List<Book> list=(List<Book>)this.bookRepository.findAll();
        return list;
    }

    //get single book by id
    public Book getBookById(int id){
        Book book=null;
        Author author =null;
        int authorid;

        try {
            //book = list.stream().filter(e -> e.getId() == id).findFirst().get();
//            authorid = Integer.valueOf(book.getAuthor_id());
            book=this.bookRepository.findById(id);
//            user=this.userRepository.findById(authorid);
//            System.out.println(authorid);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return book;
    }
    public Book getBook(String title) {
        Book book = bookRepository.findByTitleLike(title);

        return book;
    }

    //Adding the book
    public Book addBook(Book b){
        Book result=bookRepository.save(b);
        return result;
    }
    //delete book
    public void deleteBook(int bid){
//        list.stream().filter(book -> book.getId()!=bid).
//        collect(Collectors.toList());
        bookRepository.deleteById(bid);
    }

    //update book
    public void updateBook(Book book, int bookId) {
//        list=list.stream().map(b->{
//            if (b.getId()==bookId){
//                b.setTitle(book.getTitle());
//                b.setAuthor(book.getAuthor() );
//            }
//          return b;
//        }).collect(Collectors.toList());
        book.setId(bookId);
        bookRepository.save(book);
    }

//    public void updateRating(Book book, int bookId) {
//       Book b=bookRepository.findById(bookId);
//       b.setRating(book.getRating());
//       bookRepository.save(b);
//    }

    public Book getJson(String book)
    {
        Book bookJson=new Book();
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            bookJson=objectMapper.readValue(book,Book.class);
        }
        catch(IOException e)
        {
            System.out.printf("Error",e.toString());
        }
        return bookJson;

    }

}
