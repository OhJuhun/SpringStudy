package com.example.testproj.testproj.repository

import com.example.testproj.testproj.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>,JpaSpecificationExecutor<User>