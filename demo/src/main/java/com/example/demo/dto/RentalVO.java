package com.example.demo.dto;

import java.time.LocalDate;

public class RentalVO {
    //userNickname, bookName, RentDate,ReturnDate

    private RentalVO(){

    }
    public static RentalVO createRentalVO(String userNickname,String bookName,LocalDate rentDate, LocalDate returnDate){
        RentalVO rentalVO = new RentalVO();
        rentalVO.userNickname=userNickname;
        rentalVO.bookName=bookName;
        rentalVO.rentDate=rentDate;
        rentalVO.returnDate=returnDate;
        return rentalVO;

    }
    private String userNickname;

    private String bookName;

    private LocalDate rentDate;

    private LocalDate returnDate;

    public String getUserNickname() {
        return userNickname;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}
