package com.example.testproj.testproj.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="book")
data class Book(
        @Id
        @Column(name="id")
        val id: String,
        @Column(name="name")
        val name: String,
        @Column(name="isbn")
        val isbn: String,
        @Column(name="content")
        val content: String
)