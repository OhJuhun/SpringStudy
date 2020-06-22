package com.example.demo.service;


import com.example.demo.entity.Book;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, BookRepository bookRepository){
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
    }

    @Transactional
    public void insertRental(Map<String, Object> body){
        Integer uid = (Integer)body.get("user_id");
        Integer bid = (Integer)body.get("book_id");

        Long userId = uid.longValue();
        Long bookId = bid.longValue();
        Optional<User> user = userRepository.findById(userId);
        Optional<Book> book = bookRepository.findById(bookId);
        Rental rental = Rental.createRental(user.get(),book.get());
        rentalRepository.save(rental);
    }

    @Transactional
    public void modifyRental(Rental rental){
        //TODO  write how to modify rental info
    }
    @Transactional
    public void deleteRental(Long id){
        rentalRepository.deleteById(id);
    }

    @Transactional
    public void returnBook(Map<String, Object> body){
        Integer uid = (Integer)body.get("user_id");
        Integer bid = (Integer)body.get("book_id");

        Long userId = uid.longValue();
        Long bookId = bid.longValue();
        //이 둘을 기반으로 Query를 하려면 query dsl이 필요
        //userId = user_id and bookId = book_id and return_date = null

    }
}
