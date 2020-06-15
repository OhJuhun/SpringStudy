package com.example.demo.service;


import com.example.demo.entity.Rental;
import com.example.demo.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    @Autowired
    RentalRepository rentalRepository;

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
    }

    public void insertRental(Rental rental){
        rentalRepository.save(rental);
    }

    @Transactional
    public void modifyRental(Rental rental){
        //TODO  write how to modify rental info
    }
    @Transactional
    public void deleteRental(Long id){
        rentalRepository.deleteById(id);
    }
}
