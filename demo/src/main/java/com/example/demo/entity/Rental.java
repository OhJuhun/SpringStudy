package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="rental") //주인 Entity
public class Rental {

    protected Rental(){
        //생성자로 생성하면 안되고 createRental로 생성
    }
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

    @ManyToOne(targetEntity = User.class, /*fetch=FetchType.LAZY,*/ cascade =CascadeType.ALL) //save시 같이 save
    @JoinColumn(name="user_id",insertable = false, updatable = false)
    private User user;

    @ManyToOne(targetEntity = Book.class, /*fetch=FetchType.LAZY,*/ cascade =CascadeType.ALL) //무분별하게 사용은 ㄴㄴ
    @JoinColumn(name="book_id",insertable = false, updatable = false)                   //다른 class에서 참조하면 각각 persist
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    //create method
    public static Rental createRental(User user, Book book){
        Rental rental = new Rental();
        rental.setUserId(user.getId());
        rental.setBookId(book.getId());
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentDate(LocalDate.now());
        book.rentBook(1L);

        return rental;
    }

    //business Logic
    //return book
    public void renturnBook(){
        this.setReturnDate(LocalDate.now());
        this.book.returnBook(1L);
    }

//    //Rental status Search
//    public List<Rental> findRentals(RentalSearch rentalSearch){
        //TODO rental info (user_name, book_name,rental_date)
//        return rentalRepository();
//    }


}
