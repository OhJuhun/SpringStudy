package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    private ResponseEntity<List<Book>> getBooks(){
      //  new ResponseEntity<List<Book>>(null,must HttpHeaders, HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping
    private ResponseEntity insertBook(@RequestBody Book book){
        ResponseEntity responseEntity = null;
        try {
            bookService.insertBook(book);
            responseEntity = new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){ //insert시 unique여야 하는 값이 중복될 수 있는  경우 Exception
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.set(e.toString(),null);
            responseEntity = new ResponseEntity(e.toString(),httpHeader,HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @PutMapping
    private void modifyBook(Book book){
        bookService.modifyBook(book);
    }

    @DeleteMapping
    private void deleteBook(Long isbn){
        bookService.deleteBook(isbn);
    }
}
