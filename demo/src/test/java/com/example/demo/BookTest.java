package com.example.demo;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookTest {
    @Autowired
    BookService bookService;

    @Test
    void insertTest(){
        Book book = new Book();
        book.setIsbn("100000000001");
        book.setName("Java 8");
        book.setQuantity(3L);

        bookService.insertBook(book);
    }
}
