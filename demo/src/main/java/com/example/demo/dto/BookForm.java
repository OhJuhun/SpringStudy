package com.example.demo.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookForm {

    private Long id; //수정이 있으므로

    @NotEmpty(message="도서명은 필수입니다.")
    private String name;

    @Size(message="ISBN 12자리는 필수입니다.",min=12,max=12)
    private String isbn;

    @NotNull(message="수량은 필수입니다.")
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
