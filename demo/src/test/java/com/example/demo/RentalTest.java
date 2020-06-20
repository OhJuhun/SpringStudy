package com.example.demo;


import com.example.demo.entity.Rental;
import com.example.demo.repository.RentalRepository;
import com.example.demo.service.RentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
public class RentalTest {
    @Autowired
    RentalService rentalService;

    @Test
    void insertTest(){

//        Rental rental = Rental.createRental
//        rental.setId(1L);
//        rental.setBookId(1L);
//        rental.setUserId(1L);
//        rental.setRentDate(LocalDate.now());
//        rentalService.insertRental(rental);

    }
}
