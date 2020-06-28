package com.example.demo.service;


import com.example.demo.entity.Book;
import com.example.demo.entity.Rental;
import com.example.demo.dto.RentalSearch;
import com.example.demo.entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.RentalRepository;
import com.example.demo.repository.RentalSupportRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    @Autowired
    private final RentalSupportRepository rentalSupportRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository,
                         UserRepository userRepository,
                         BookRepository bookRepository,
                         RentalSupportRepository rentalSupportRepository){
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.rentalSupportRepository = rentalSupportRepository;
    }

    public List<Rental> getRentals(){
        return rentalRepository.findAll();
    }

    @Transactional
    public void insertRental(Map<String, Object> body){
        Long uid = (Long)body.get("user_id");
        Long bid = (Long)body.get("book_id");

        Optional<User> user = userRepository.findById(uid);
        Optional<Book> book = bookRepository.findById(bid);
        Rental rental = Rental.createRental(user.get(),book.get());
        rentalRepository.save(rental);
        //성공이지만 이렇게 하면 안됨!!
    }

    @Transactional
    public void returnRental(Long id){
        //TODO  write how to modify rental info
        Optional<Rental> rental = rentalRepository.findById(id);
        rental.get().setReturnDate(LocalDate.now());
        rental.get().getBook().setQuantity(rental.get().getBook().getQuantity()+1);
        rentalRepository.save(rental.get());
    }
    @Transactional
    public void deleteRental(Long id){
        rentalRepository.deleteById(id);
    }

    @Transactional
    public void returnBook(Map<String, Object> body){
        Integer uid = (Integer)body.get("user_id");
        Integer bid = (Integer)body.get("book_id");

        Long userId = uid.longValue();
        Long bookId = bid.longValue();
        //이 둘을 기반으로 Query를 하려면 query dsl이 필요
        //userId = user_id and bookId = book_id and return_date = null
    }


    public List<Rental> findMyRentals(RentalSearch rentalSearch){
        return rentalSupportRepository.findByRental_rentalSearch(rentalSearch);
    }
}
