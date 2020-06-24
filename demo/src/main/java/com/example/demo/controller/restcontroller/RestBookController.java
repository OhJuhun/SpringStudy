package com.example.demo.controller.restcontroller;

import com.example.demo.entity.Book;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.sun.media.sound.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class RestBookController {
    @Autowired
    BookService bookService;

    //CRUD만 만들기

    /*
     * TODO : CREATE NEW BOOK INFO
     *  Created 201, Processed but not created 200, No Result to Return 204
     *  Invalid Data 400
     */
    @PostMapping
    public ResponseEntity createBook(@RequestBody Book book){
        try {
            bookService.insertBook(book);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
    /*
     * TODO : GET ALL BOOKS
     *  Return 200
     *  Not Found 404
     */
    @GetMapping
    public ResponseEntity getAllBooks(){
        List<Book> books = bookService.getBooks();
        if(books==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(books,HttpStatus.OK);
    }

    /*
     * TODO : GET ONE BOOK BY ISBN
     *  Return 200
     *  Not Found 404
     */
    @GetMapping("/{isbn}") //return 200
    public ResponseEntity getBookByIsbn(@PathVariable("isbn") String isbn){
        Book book = bookService.getByIsbn(isbn);
        if(book==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(book,HttpStatus.OK);
    }

    /*
     * TODO : MODIFY BOOK INFO
     *  Created 201, Updated 200 or 201, No Result to Return 204
     *  Invalid Data 400, Conflict 409
     */
    @PutMapping("/{id}")
    public ResponseEntity modifyBook(@RequestBody Book book,
                                     @PathVariable("id") Long id){
        try{
            bookService.modifyBook(id,book);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /*
     * TODO : DELETE BOOK BY ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id){
        return new ResponseEntity(HttpStatus.OK);
    }
}
