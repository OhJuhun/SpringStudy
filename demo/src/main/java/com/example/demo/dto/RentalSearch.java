package com.example.demo.dto;

import com.example.demo.entity.RentalStatus;

public class RentalSearch {
    private String userName; //유저 명으로 검색이 존재함
    private RentalStatus rentalStatus; //Rental 상태로 검색이 존재함

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
