package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Optional<Book> getByIsbn(String book) {
        //optional 사용 : 없으면 null return 아닐 경우 없으면 [] 리턴
        return bookRepository.findByIsbn(book);
    }

    public void insertBook(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long isbn){
        //TO DO : delete Using isbn
    }

    @Transactional
    public void modifyBook(Book book){
        //TO DO : modify book info
    }


}
