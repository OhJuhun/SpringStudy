package com.example.demo.controller;


public class BookForm {

    private Long id; //수정이 있으므로

    private String name;

    private String isbn;

    private Long quantity;

    public BookForm(){}


    public BookForm(Long id,String name, String isbn, Long quantity){
        this.id=id;
        this.name=name;
        this.isbn = isbn;
        this.quantity = quantity;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
