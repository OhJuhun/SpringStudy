package com.example.demo.controller;

import com.example.demo.entity.Rental;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private ResponseEntity<Rental> rentBook(@RequestBody Map<String, Object> body){ //그냥 Json으로 묶어서 보내는거보다 낫나
        ResponseEntity<Rental> responseEntity = new ResponseEntity<Rental>(HttpStatus.OK);
        try {
            rentalService.insertRental(body);
        } catch(Exception e){
            responseEntity = new ResponseEntity(e.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }

    @PutMapping
    private ResponseEntity<Rental> returnBook(@RequestBody Map<String,Object> body){
        ResponseEntity<Rental> responseEntity= new ResponseEntity<Rental>(HttpStatus.OK);
        try{
            rentalService.returnBook(body);
        }catch(Exception e){

        }
        return responseEntity;
    }

    @DeleteMapping //지울 일은 없음
    private ResponseEntity<Rental> delete(@RequestParam Long id){
        ResponseEntity<Rental> responseEntity = new ResponseEntity<Rental>(HttpStatus.OK);
        return responseEntity;
    }
}
