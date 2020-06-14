package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(bookService.getBooks());
    }

    @PostMapping
    private void insertBook(Book book){
        bookService.insertBook(book);
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
