package com.example.testproj.testproj

import com.example.testproj.testproj.service.BookService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class BookTest @Autowired constructor(
        val bookService: BookService //앞에 변수 표기를 꼭 해줘야 함
){
    @Test
    internal fun getBookByIdTest(){
        //repository.findBookByIdOrNull이면 book.property가 가능하나
        //이렇게 짜면 불가능함. 이유가머지
    }
    @Test
    internal fun getBookTest(){

    }

    @Test
    internal fun setBookTest(){

    }
}