package com.example.demo.repository;

import com.example.demo.entity.Rental;
import com.example.demo.entity.RentalSearch;
import com.example.demo.predicate.RentalPredicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import java.util.List;

import static com.example.demo.entity.QRental.rental;

@Repository
public class RentalSupportRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public RentalSupportRepository(JPAQueryFactory jpaQueryFactory){
        super(Rental.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Rental> findByRental_rentalSearch(RentalSearch rentalSearch){ //_가 예약어 이므로 사용해야함
        System.out.println("USERNAME: "+rentalSearch.getUserName());
        return jpaQueryFactory
                .select(rental)
                .from(rental)
                .where(RentalPredicate.findByRentalSearch(rentalSearch))
                .fetch();
    }
}
