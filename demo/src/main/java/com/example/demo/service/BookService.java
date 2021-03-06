package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository; //생성자에서 주입
    }
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getByIsbn(String isbn) {
        //optional 사용 : 없으면 null return 사용하지 않을 경우 없으면 [] 리턴
        return bookRepository.findByIsbn(isbn);
    }

    public Book getByName(String name){
        return bookRepository.findByName(name);
    }

    @Transactional
    public Book insertBook(Book book){
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book modifyBook(Long id,Book paramBook){
        //Id값과 book을 받아와서 Id로 persistent entity를 검색 후,
        // 변경 값을 대입하면 save안해도 알아서 변경 (변경감지)
        Book book = bookRepository.getOne(id);

        if(paramBook.getName()!=null) book.setName(paramBook.getName());
        if(paramBook.getIsbn()!=null) book.setIsbn(paramBook.getIsbn());
        if(paramBook.getQuantity()!=null) book.setQuantity(paramBook.getQuantity());

        return bookRepository.save(book);
    }

    public Optional<Book> findOne(Long id){ return bookRepository.findById(id);}
}
