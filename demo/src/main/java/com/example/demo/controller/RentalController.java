package com.example.demo.controller;

import com.example.demo.entity.Rental;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {
    @Autowired
    RentalService rentalService;

    @GetMapping
    private ResponseEntity<List<Rental>> getAll(){
        return ResponseEntity.ok(rentalService.getRentals());
    }

    @PostMapping
    private void insert(Rental rental){
        rentalService.insertrRental(rental);
    }

    @PutMapping
    private void modify(Rental rental){
        rentalService.modifyRental(rental);
    }

    @DeleteMapping
    private void delete(Long id){
        rentalService.deleteRental(id);
    }
}
