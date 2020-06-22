package com.example.demo.predicate;

import com.example.demo.entity.RentalSearch;
import com.querydsl.core.BooleanBuilder;


import static com.example.demo.entity.QRental.rental;

public class RentalPredicate {
    public static BooleanBuilder findByRentalSearch(RentalSearch rentalSearch){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(rentalSearch.getUserName()!=null) booleanBuilder.and(rental.user.name.eq(rentalSearch.getUserName()));

        return booleanBuilder;
    }
}
