package com.example.demo.service;


import com.example.demo.entity.Rental;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void insertRental(Rental rental){
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
}
