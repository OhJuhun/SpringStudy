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
        val id: Long ? = null, //null로 생성해야 identity를 통해 증가

        @Column(name="uid")
        val uid: String,
        @Column(name="name")
        val name: String,

        @Column(name="password")
        val password: String,

        @Column(name="current")
        val current: Long = 0
)
{

        override fun toString() = kotlinToString(properties = toStringProperties)
        override fun equals(other :Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)
        override fun hashCode() = kotlinHashCode(properties = equalsAndHashCodeProperties)

        companion object{
                private val equalsAndHashCodeProperties = arrayOf(User::id)
                private val toStringProperties = arrayOf(
                        User::id,
                        User::uid,
                        User::name,
                        User::password,
                        User::current
                )
        }

}