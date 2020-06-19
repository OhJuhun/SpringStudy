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
    private ResponseEntity<Rental> insert(@RequestBody Map<String, Object> body){ //그냥 Json으로 묶어서 보내는거보다 낫나
        ResponseEntity<Rental> responseEntity = new ResponseEntity<Rental>(HttpStatus.OK);

        return responseEntity;
    }

    @PutMapping
    private ResponseEntity<Rental> modify(@RequestBody Map<String,Object> body){
        ResponseEntity<Rental> responseEntity= new ResponseEntity<Rental>(HttpStatus.OK);
        //TODO modify what?
        return responseEntity;
    }

    @DeleteMapping
    private ResponseEntity<Rental> delete(@RequestParam Long id){
        ResponseEntity<Rental> responseEntity = new ResponseEntity<Rental>(HttpStatus.OK);
        return responseEntity;
    }
}
