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

    fun getBookById(id : Long) : ResponseEntity<*>?{
        val book = bookRepository.findById(id)
        //findByIdOrNull로하면 return이 불가능 -->!!를 붙이면 가능. 근데 의미가 있나
        println(book.toString())
        return ResponseEntity.ok(book)
    }

    fun setBook(book : Book) : ResponseEntity<*>{
        val src = bookRepository.save(book)
        return ResponseEntity.ok(src)
    }
}