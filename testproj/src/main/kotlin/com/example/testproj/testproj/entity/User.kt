package com.example.testproj.testproj.entity

import javax.persistence.*

@Entity
@Table(name="user")
data class User(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name="id")
        val id: Long,

        @Column(name="name")
        val name: String,

        @Column(name="birthDate")
        val birthDate: String?
)