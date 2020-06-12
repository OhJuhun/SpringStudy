package com.example.testproj.testproj.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="rental")
data class Rental(
        @Id
        @Column(name="id")
        val id: String,

        @Column(name="userId")
        val userId: String,

        @Column(name="bookId")
        val bookId: String,

        @Column(name="inDate")
        val inDate: String,

        @Column(name="outDate")
        val outDate: String,

        @Column(name="arrears")
        val arrears: String
)