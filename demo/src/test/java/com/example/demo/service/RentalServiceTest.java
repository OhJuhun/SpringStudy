package com.example.demo.service;


import com.example.demo.AbstractControllerTest;
import com.example.demo.entity.Rental;
import com.example.demo.entity.RentalSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
public class RentalServiceTest{
    @Autowired
    RentalService rentalService;


    @Test
    void tdd() throws Exception{
        //given
        RentalSearch rentalSearch = new RentalSearch();


        //when
        List<Rental> rentalList = rentalService.findMyRentals(rentalSearch);

        //then

        System.out.println(rentalList);

    }
}
