package com.example.testproj.testproj.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import javax.persistence.*

@Entity
@Table(name="book")
class Book(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name="id")
        val id: Long ? = null,
        @Column(name="name")
        val name: String,
        @Column(name="isbn")
        val isbn: String,
        @Column(name="quantity")
        val quantity: Long
){
        override fun toString() = kotlinToString(properties = toStringProperties)
        override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)
        override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

        companion object{
                private val equalsAndHashCodeProperties = arrayOf(Book::id)
                private val toStringProperties = arrayOf(
                        Book::id,
                        Book::name,
                        Book::isbn,
                        Book::quantity
                )
        }
}