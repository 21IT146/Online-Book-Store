package com.book.store.Controllers;

import com.book.store.Entities.Book;
import com.book.store.Entities.BookPrice;
import com.book.store.Entities.Bookhistory;
import com.book.store.Entities.PriceHistory;
import com.book.store.Services.BookPriceService;
import com.book.store.Services.BookServices;
import com.book.store.Services.FileService;
import com.book.store.dao.BookPriceRepository;
import com.book.store.dao.BookRepository;
import com.book.store.dao.BookhistoryRepository;
import com.book.store.helper.FileUploadhelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookhistoryRepository bookhistoryRepository;
    @Autowired
    private BookServices bookServices;

    @Autowired
    private FileUploadhelper fileUploadhelper;

    @Autowired
    private FileService fileService;

    @Value("${book.image.path}")
    private String imageUploadPath;
    @Autowired
    private BookPriceService bookPriceService;
    @Autowired
    private BookPriceRepository bookPriceRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBook()//@RequestHeader(value="accessKey") String accessKey in getBook()
    {

        //String accKey="123";
//            if(accKey.equals(accessKey))
//            {
        List<Book> list= this.bookServices.getAllBooks();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
//            else
//            {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//           }


    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id)
    {

        Book book=bookServices.getBookById(id);

        if(book==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));


    }

    @GetMapping("/images/{FileUrl}")
    public void getFile(@PathVariable("FileUrl") String FileUrl, HttpServletResponse response) throws IOException {
        InputStream inputStream = fileService.getResource(imageUploadPath,FileUrl); //this just gets the data from a database
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream,response.getOutputStream());
    }

    //new book handler
//    @PostMapping("/books")
//    public ResponseEntity<Book> addBook(@RequestBody Book book){
//
//        Book b1=null;
//        try {
//            b1 = this.bookServices.addBook(book);
//            System.out.println(book);
//            return ResponseEntity.of(Optional.of(b1));
//             //return ResponseEntity.status(HttpStatus.CREATED).build();
//
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestParam("book") String book,@RequestParam("file") MultipartFile file) throws IOException {
        String s = fileService.uploadFile(file,imageUploadPath);

        Book b=bookServices.getJson(book); //1
        //OR two ways to convert json object
        //Book b=new Book(book); //2
//        int id=b.getId();
//        BookPrice bp=this.bookPriceRepository.findById(id);
//        bp.setLastPrice(b.getCurrPrice());
//        System.out.println(bp.getLastPrice());
        b.setFileUrl(s);
        Book b1=null;
        try {
            b1 = this.bookServices.addBook(b);
            System.out.println(book);

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            if (!file.getContentType().equals("image/jpeg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            //file upload code

            boolean f=fileUploadhelper.uploadFIle(file);
            if(f)
            {
                //return ResponseEntity.ok("File is successfully uploaded");
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }

            return ResponseEntity.of(Optional.of(b1));
            //return ResponseEntity.status(HttpStatus.CREATED).build();

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/searchbook")
    public ResponseEntity<Book> searchbook(@RequestParam(name="title") String title)
    {

        Book book =bookServices.getBook(title);

        if(book ==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    //delete book handler
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
        try {
            this.bookServices.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update book handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){

        try {
            this.bookServices.updateBook(book, bookId);
            return ResponseEntity.ok().body(book);
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/booksPrice/{bookId}")
    public ResponseEntity<Book> updateBookPrice(@RequestBody Book book,@PathVariable("bookId") int bookId){

//        Date time=new Date();
//        Timestamp ts=new Timestamp(time.getTime());
//        Book b=this.bookRepository.findById(bookId);
//        PriceHistory ph=new PriceHistory();
//        ph.setPrice(b.getCurrPrice());
//        ph.setTime(ts);
//
//        Bookhistory bh=this.bookhistoryRepository.findById(bookId);
//        bh.getPricehistory().add(ph);
//
//        System.out.println(bh);
        try {
            Book b=this.bookRepository.findById(bookId);
            BookPrice bp=this.bookPriceRepository.findById(bookId);
            bp.setLastPrice(b.getCurrPrice());
            this.bookPriceRepository.save(bp);

            //this.bookServices.updateBook(book, bookId);
            b.setCurrPrice(book.getCurrPrice());
            this.bookRepository.save(b);
//            bp.setAvgPrice((bp.getAvgPrice()+b.getCurrPrice())/2);
//            this.bookPriceRepository.save(bp);
            return ResponseEntity.ok().body(book);
        }catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

//    @PutMapping("/booksRating/{bookId}")
//    public ResponseEntity<Book> updateRating(@RequestBody Book book,@PathVariable("bookId") int bookId){
//        try {
//            this.bookServices.updateRating(book, bookId);
//            return ResponseEntity.ok().body(book);
//        }catch(Exception e)
//        {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }

}