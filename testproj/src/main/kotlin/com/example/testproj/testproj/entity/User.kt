package com.example.testproj.testproj.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import javax.persistence.*

@Entity
@Table(name="user")
class User(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name="id")
        private val id: Long ? = null,

        @Column(name="name")
        private val name: String,

        @Column(name="birthDate")
        private val birthDate: String?
)
{
        override fun toString() = kotlinToString(properties = toStringProperties)
        override fun equals(other :Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)
        override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

        companion object{
                private val equalsAndHashCodeProperties = arrayOf(User::id)
                private val toStringProperties = arrayOf(
                        User::id,
                        User::name,
                        User::birthDate
                )
        }
}