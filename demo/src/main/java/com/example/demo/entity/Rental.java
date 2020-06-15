package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id = null;

    @Column(name="userid",length = 20, nullable = false)
    private String userId;

    @Column(name="bookid",length = 20, nullable = false)
    private String bookId;

    @Column(name="rentdate", nullable = true)
    private LocalDate rentDate;

    @Column(name="returndate", nullable = true)
    private LocalDate returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
