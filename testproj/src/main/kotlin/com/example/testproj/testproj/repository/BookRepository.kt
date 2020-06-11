package com.example.testproj.testproj.repository

import com.example.testproj.testproj.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, String>