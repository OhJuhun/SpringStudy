package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.Book
import com.example.testproj.testproj.repository.BookRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository : BookRepository){
    fun getBooks() :List<Book>{
        val books = bookRepository.findAll()
        return books
    }

    fun setBook(book : Book) : Boolean{
        bookRepository.save(book)
        return true
    }
}