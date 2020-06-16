package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="rental") //주인 Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rental_id", nullable = false)
    private Long id = null;

    @Column(name="user_id",length = 20, nullable = false)
    private Long userId;

    @Column(name="book_id",length = 20, nullable = false)
    private Long bookId;

    @Column(name="rent_date", nullable = true)
    private LocalDate rentDate;

    @Column(name="return_date", nullable = true)
    private LocalDate returnDate;

    @ManyToOne(targetEntity = User.class, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",insertable = false, updatable = false)
    private User user;

    @ManyToOne(targetEntity = Book.class, fetch=FetchType.LAZY)
    @JoinColumn(name="book_id",insertable = false, updatable = false)
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
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
