package com.example.testproj.testproj

import com.example.testproj.testproj.entity.Rental
import com.example.testproj.testproj.service.RentalService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class RentalTest @Autowired constructor(
        val rentalService: RentalService
){
    @Test
    internal fun getRentalByIdTest(){

    }

    @Test
    internal fun getRentalTest(){

    }

    @Test
    internal fun setRentalTest(){
        //
    }
}