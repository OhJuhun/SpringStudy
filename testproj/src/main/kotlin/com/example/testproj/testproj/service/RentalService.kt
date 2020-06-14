package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.Rental
import com.example.testproj.testproj.repository.RentalRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class RentalService(private val rentalRepository: RentalRepository){
    fun getRentals(): List<Rental> {
        val rentals = rentalRepository.findAll()
        return rentals
    }

    fun setRental(inRental : Rental) :Boolean{
        var rental = Rental(inRental.id,inRental.userId,inRental.bookId, LocalDate.now())
        try {
            rentalRepository.save(rental)
        }catch(e:Exception){
            return false;
        }
        return true
    }


}