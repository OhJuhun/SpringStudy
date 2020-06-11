package com.example.testproj.testproj.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="user")
data class User(
        @Id
        @Column(name="id")
        val id: String,

        @Column(name="name")
        val name: String,

        @Column(name="birthDate")
        val birthDate: String
)