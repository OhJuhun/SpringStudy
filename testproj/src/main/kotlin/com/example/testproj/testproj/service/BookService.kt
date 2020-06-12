package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.Book
import com.example.testproj.testproj.repository.BookRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository : BookRepository){
    fun getBook() : ResponseEntity<*>{
        val books = bookRepository.findAll()
        return ResponseEntity.ok(books)
    }

    fun setBook(book : Book) : ResponseEntity<*>{
        //if(book.content==null) book.content=""
        val src = bookRepository.save(book)
        return ResponseEntity.ok(src)
    }
}