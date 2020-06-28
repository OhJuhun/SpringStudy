package com.example.demo.vo;

import java.time.LocalDate;
import java.util.Objects;

public class RentalVO {
    //userNickname, bookName, RentDate,ReturnDate

    private RentalVO(Long id,String userNickname,String bookName,LocalDate rentDate, LocalDate returnDate){
        //기본생성자
        this.id=id;
        this.userNickname=userNickname;
        this.bookName=bookName;
        this.rentDate=rentDate;
        this.returnDate=returnDate;
    }

    private Long id;
    private String userNickname;

    private String bookName;

    private LocalDate rentDate;

    private LocalDate returnDate;
    // ***** Only getter ***** -> READ ONLY
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

    public static RentalVO createRentalVO(Long id, String userNickname,String bookName,LocalDate rentDate, LocalDate returnDate){
        RentalVO rentalVO = new RentalVO(id,userNickname, bookName, rentDate, returnDate);
        return rentalVO;

    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        RentalVO rentalVO = (RentalVO) o;

        return (Objects.equals(id,rentalVO.id)
                && Objects.equals(userNickname,rentalVO.userNickname) //VO는 모든 인자를 다 비교해야 함
                && Objects.equals(bookName,rentalVO.bookName)
                && Objects.equals(rentDate,rentalVO.rentDate)
                && Objects.equals(returnDate,rentalVO.returnDate));
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
