package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/getByIsbn")
    private ResponseEntity<Book> getByIsbn(@RequestParam String isbn){ //무조건 하나만 return
        return ResponseEntity.ok(bookService.getByIsbn(isbn));
    }

    @GetMapping("/getByName")
    private ResponseEntity<Book> getByName(@RequestParam String name){
        return ResponseEntity.ok(bookService.getByName(name));
    }
    @PostMapping
    private ResponseEntity<Book> insertBook(@RequestBody Book book){
        //uses unchecked or unsafe operations 경고 제거를 위해 raw -> String Type 지정
        ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(HttpStatus.OK);

        try {
            responseEntity = responseEntity.ok(bookService.insertBook(book));
        }catch (DataIntegrityViolationException e){ //데이터 통합 오류?
            responseEntity = new ResponseEntity<Book>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        catch (Exception e){ //insert시 unique여야 하는 값이 중복될 수 있는  경우 Exception
            responseEntity = new ResponseEntity<Book>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return responseEntity;
    }

    @PatchMapping("/modifyName")
    private ResponseEntity<Book> modifyBookName(@RequestParam String isbn,
                                                @RequestParam String newName){
        ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(HttpStatus.OK);
        try {
            Book book = bookService.getByIsbn(isbn);
            if(book==null){
                throw new Exception("Book Not Found");
            }
            book.setName(newName);
            responseEntity = responseEntity.ok(bookService.modifyBook(book));
        } catch(Exception e){
            responseEntity = new ResponseEntity<Book>(HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @PatchMapping("/modifyIsbn")
    private ResponseEntity<Book> modifyBookIsbn(@RequestParam String isbn,
                                                @RequestParam String newIsbn){
        ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(HttpStatus.OK);
        try{
            Book book = bookService.getByIsbn(isbn);
            if(book==null){
                throw new Exception("Book Not Found");
            }
            book.setIsbn(newIsbn);
            responseEntity = responseEntity.ok(bookService.modifyBook(book));
        } catch(Exception e){
            responseEntity = new ResponseEntity<Book>(HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @PatchMapping("/modifyQuantity")
    private ResponseEntity<Book> modifyBookQuantity(@RequestParam String isbn,
                                                    @RequestParam Long newQuantity){
        ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(HttpStatus.OK);
        try{
            Book book = bookService.getByIsbn(isbn);
            if(book==null){
                throw new Exception("Book Not Found");
            }
            book.setQuantity(newQuantity);
            responseEntity = responseEntity.ok(bookService.modifyBook(book));
        } catch(Exception e){
            responseEntity = new ResponseEntity<Book>(HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @DeleteMapping
    private ResponseEntity<Book> deleteBook(@RequestParam(name="isbn") String isbn){
        ResponseEntity<Book> responseEntity = new ResponseEntity<Book>(HttpStatus.OK);
        try{
            Book book = bookService.getByIsbn(isbn);
            if(book == null){
                throw new Exception("Book Not Found");
            }
            bookService.deleteBook(book.getId());
        } catch(Exception e){
            responseEntity = new ResponseEntity<Book>(HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
