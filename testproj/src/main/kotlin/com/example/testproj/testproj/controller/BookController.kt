package com.example.testproj.testproj.controller

import com.example.testproj.testproj.entity.Book
import com.example.testproj.testproj.repository.BookRepository
import com.example.testproj.testproj.service.BookService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(val bookService: BookService){
    @GetMapping
    fun getBooks() : ResponseEntity<*>{
        val books = bookService.getBook()
        return ResponseEntity.ok(books)
    }

    @PostMapping
    fun setBooks(@RequestBody book : Book) : ResponseEntity<*>{
        val src = bookService.setBook(book)
        return ResponseEntity.ok(src)
    }
}