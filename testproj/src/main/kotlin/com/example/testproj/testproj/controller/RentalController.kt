package com.example.testproj.testproj.controller

import com.example.testproj.testproj.entity.Rental
import com.example.testproj.testproj.repository.RentalRepository
import com.example.testproj.testproj.service.RentalService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rental")
class RentalController(val rentalService: RentalService){
    @GetMapping
    fun getRental() : ResponseEntity<*> { //모든 rental 정보 조회
        val rentals = rentalService.getRentals()
        return ResponseEntity.ok(rentals)
    }

    @PostMapping //Rental 정보 삽임
    fun setRental(@RequestBody rental : Rental) : ResponseEntity<*>{
        val src = rentalService.setRental(rental)
        return ResponseEntity.ok(src)
    }
}