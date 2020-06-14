package com.example.demo.service;


import com.example.demo.entity.Rental;
import com.example.demo.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
    }

    public void insertrRental(Rental rental){
        rentalRepository.save(rental);
    }

    public void modifyRental(Rental rental){
        //TODO  write how to modify rental info
    }

    public void deleteRental(Long id){
        rentalRepository.deleteById(id);
    }
}
