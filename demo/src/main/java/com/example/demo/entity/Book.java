package com.example.demo.entity;


import com.example.demo.exception.NotEnoughQuantityException;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {


    protected Book(){
        
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id", nullable=false)
    private Long id = null;

    @Column(name="name", length=100, nullable = false)
    private String name;

    @Column(name="isbn",length=12, nullable = false)
    private String isbn;

    @Column(name="quantity", nullable = true)
    private Long quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(Long quantity){
        this.quantity += quantity;
    }

    public void removeQuantity(Long quantity){
        if(this.quantity-quantity<0) throw new NotEnoughQuantityException("there is no book");
        this.quantity -= quantity;
    }

    //재고 수량 복귀
    public void returnBook(Long quantity){
        this.addQuantity(quantity);
    }

    public void rentBook(Long quantity){
        this.removeQuantity(quantity);
    }


    //create Book
    public static Book createBook(String name, String isbn, Long quantity){
        Book book= new Book();
        book.setName(name);
        book.setIsbn(isbn);
        book.setQuantity(quantity);
        return book;
    }
}
