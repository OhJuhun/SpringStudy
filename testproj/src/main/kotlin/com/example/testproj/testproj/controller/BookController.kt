package com.example.testproj.testproj.controller

import com.example.testproj.testproj.entity.Book
import com.example.testproj.testproj.repository.BookRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(val bookRepository: BookRepository){
    @GetMapping
    fun getBooks() : ResponseEntity<*>{
        val books = bookRepository.findAll()
        return ResponseEntity.ok(books)
    }

    @PostMapping
    fun setBooks(@RequestBody book : Book) : ResponseEntity<*>{
        val books = bookRepository.save(book)
        return ResponseEntity.ok(books)
    }
}