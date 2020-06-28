package com.example.demo.controller.restcontroller;

import com.example.demo.dto.RentalSearch;
import com.example.demo.vo.RentalVO;
import com.example.demo.entity.Rental;
import com.example.demo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rental")
public class RestRentalController {
    @Autowired
    RentalService rentalService;

    /*
     * TODO : CREATE NEW RENTAL INFO
     *  Created 201, Processed but not created 200, No Result to Return 204
     *  Invalid Data 400
     */
    @PostMapping("/{userid}/{bookid}")
    public ResponseEntity createRental(@PathVariable("userid") Long userId,
                                       @PathVariable("bookid") Long bookId){
        try {
            Map<String,Object> request = new HashMap<>();
            request.put("user_id",userId);
            request.put("book_id",bookId);
            rentalService.insertRental(request);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
     * TODO : GET ALL RENTALS
     *  Return 200
     *  Not Found 404
     */
    @GetMapping
    public ResponseEntity getAllRentals(){
        List<Rental> rentals = rentalService.getRentals(); //Rental정보 get
        List<RentalVO> rentalVOs = new ArrayList<>();

        for(Rental rental : rentals){
            RentalVO rentalVO = RentalVO.createRentalVO(rental.getId()
                    ,rental.getUser().getNickname()
                    ,rental.getBook().getName()
                    ,rental.getRentDate()
                    ,rental.getReturnDate());
            rentalVOs.add(rentalVO);
        }
        if(rentals==null) return new ResponseEntity(HttpStatus.NOT_FOUND); //VO를 사용하여 Fetch= Lazy 문제 해결
        return new ResponseEntity(rentalVOs,HttpStatus.OK);
    }

    /*
     * TODO : GET ALL Rentals By UserName
     *  Return 200
     *  Not Found 404
     */
    @GetMapping("/{username}")
    public ResponseEntity getRentalsByUsername(@PathVariable("username") String username){
        RentalSearch rentalSearch = new RentalSearch();
        rentalSearch.setUserName(username);
        List<Rental> rentals = rentalService.findMyRentals(rentalSearch);
        if(rentals==null) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(rentals,HttpStatus.OK);
    }

    /*
     * TODO : Return rent book INFO
     *  Created 201, Updated 200 or 201, No Result to Return 204
     *  Invalid Data 400, Conflict 409
     */
    @PutMapping("/{userid}/{bookid}")
    public ResponseEntity returnRental(@PathVariable("userid") Long userId,
                                       @PathVariable("bookid") Long bookId){
        try{
            Map<String, Object> request = new HashMap<>();
            request.put("user_id",userId);
            request.put("book_id",bookId); //이렇게 하면 안됨
            rentalService.returnBook(request);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    //DELETE는 필요가 없음

}
