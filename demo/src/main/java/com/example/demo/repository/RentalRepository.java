package com.example.demo.repository;

import com.example.demo.entity.Rental;
import com.example.demo.entity.RentalSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {
    public List<Rental> findRentals(RentalSearch rentalSearch);
}
