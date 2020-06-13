package com.example.testproj.testproj.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name="rental")
class Rental(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name="id")
        val id: Int? = null,

        @Column(name="userId")
        val userId: String,

        @Column(name="bookId")
        val bookId: String,

        @Column(name="inDate")
        var inDate: LocalDate?,

        @Column(name="outDate")
        var outDate: LocalDate? = null,

        @Column(name="arrears")
        var arrears: String? = null
){
        override fun toString() = kotlinToString(properties = toStringProperties)

        override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

        override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

        companion object {
                private val equalsAndHashCodeProperties = arrayOf(Rental::id) //객체에 대한 equals, hashCode 함수 override를 위한 property
                private val toStringProperties = arrayOf( //객체에 대한 toString 함수 override를 위한 property
                        Rental::id,
                        Rental::userId,
                        Rental::bookId,
                        Rental::inDate,
                        Rental::outDate,
                        Rental::arrears
                )
        }

}