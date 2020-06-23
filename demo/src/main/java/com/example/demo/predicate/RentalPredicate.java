package com.example.demo.predicate;

import com.example.demo.entity.RentalStatus;
import com.querydsl.core.types.dsl.BooleanExpression;


import static com.example.demo.entity.QRental.rental;


//Null처리, 가독성을 위해 모듈화
public class RentalPredicate {

    public static BooleanExpression eqName(String name){
        if(name==null) return null;
        return rental.user.name.eq(name);
    }

    public static BooleanExpression eqStatus(RentalStatus status){
        if(status == null) return null;
        if(status.equals(RentalStatus.RENT)) return rental.returnDate.isNull();
        else return rental.returnDate.isNotNull();
    }
}
