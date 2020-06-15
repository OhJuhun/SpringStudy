package com.example.demo.controller;

import com.example.demo.entity.Rental;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {
    @Autowired
    RentalService rentalService;

    @GetMapping
    private ResponseEntity<List<Rental>> getAll(){
        return ResponseEntity.ok(rentalService.getRentals());
    }

    @PostMapping
    private ResponseEntity<String> insert(@RequestBody Rental rental){
        ResponseEntity<String> responseEntity= new ResponseEntity<String>(HttpStatus.OK);
        try {
            rentalService.insertRental(rental);
        } catch(Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("error",httpHeaders,HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @PutMapping
    private ResponseEntity<String> modify(@RequestBody Rental rental){
        ResponseEntity<String> responseEntity= new ResponseEntity<String>(HttpStatus.OK);
        try {
            rental.setRentDate(LocalDate.now());
            rentalService.modifyRental(rental);
        } catch(Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("error",httpHeaders,HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @DeleteMapping
    private ResponseEntity<String> delete(@RequestParam Long id){
        ResponseEntity<String> responseEntity= new ResponseEntity<String>(HttpStatus.OK);
        try {
            rentalService.deleteRental(id);
        } catch(Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("error",httpHeaders,HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }
}
