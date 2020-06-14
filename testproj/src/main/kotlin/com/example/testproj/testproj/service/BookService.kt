package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.Book
import com.example.testproj.testproj.repository.BookRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository : BookRepository){
    fun getBooks() : ResponseEntity<*>{
        val books = bookRepository.findAll()
        return ResponseEntity.ok(books)
    }

    fun setBook(book : Book) : ResponseEntity<*>{
        val src = bookRepository.save(book)
        return ResponseEntity.ok(src)
    }
}