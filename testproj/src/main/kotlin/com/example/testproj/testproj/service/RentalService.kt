package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.Rental
import com.example.testproj.testproj.repository.RentalRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RentalService(private val rentalRepository: RentalRepository){
    fun getRental(): ResponseEntity<*> {
        val rental = rentalRepository.findAll()
        return ResponseEntity.ok(rental);
    }

    fun setRental(rental : Rental): ResponseEntity<*>{
        val src = rentalRepository.save(rental)
        return ResponseEntity.ok(src)
    }
}