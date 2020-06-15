package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/byIsbn")
    private ResponseEntity<Optional<Book>> getByIsbn(@RequestParam String isbn){
        return ResponseEntity.ok(bookService.getByIsbn(isbn));
    }
    @PostMapping
    private ResponseEntity<String> insertBook(@RequestBody Book book){
        //uses unchecked or unsafe operations 경고 제거를 위해 raw -> String Type 지정
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try {
            bookService.insertBook(book);
        } catch (Exception e){ //insert시 unique여야 하는 값이 중복될 수 있는  경우 Exception
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("duplicated",httpHeaders,HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @PutMapping
    private ResponseEntity<String> modifyBook(Book book){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try {
            bookService.modifyBook(book);
        } catch(Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("not found",httpHeaders,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping
    private ResponseEntity<String> deleteBook(Long isbn){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try{
            bookService.deleteBook(isbn);
        } catch(Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("not found",httpHeaders,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
