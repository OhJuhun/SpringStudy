package com.example.testproj.testproj.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="rental")
data class Rental(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="id")
        val id: Int,

        @Column(name="userId")
        val userId: String,

        @Column(name="bookId")
        val bookId: String,

        @Column(name="inDate")
        var inDate: LocalDate?,

        @Column(name="outDate")
        val outDate: LocalDate?,

        @Column(name="arrears")
        var arrears: String?
)