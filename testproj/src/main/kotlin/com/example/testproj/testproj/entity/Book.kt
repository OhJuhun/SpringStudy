package com.example.testproj.testproj.entity

import javax.persistence.*

@Entity
@Table(name="book")
data class Book(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name="id")
        val id: Int,
        @Column(name="name")
        val name: String,
        @Column(name="isbn")
        val isbn: String,
        @Column(name="content")
        val content: String?
)