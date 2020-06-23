package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class RentalForm {
    private Long id;

    @NotNull(message="사용자 정보는 필수입니다.")
    private Long userId;
    @NotNull(message="도서 정보는 필수입니다.")
    private Long bookId;

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
}

