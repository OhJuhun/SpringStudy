package com.example.testproj.testproj.repository

import com.example.testproj.testproj.entity.Rental
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RentalRepository : JpaRepository<Rental,Long>
