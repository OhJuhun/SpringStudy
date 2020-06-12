package com.example.testproj.testproj.service

import com.example.testproj.testproj.entity.User
import com.example.testproj.testproj.repository.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository){
    fun getUser() : ResponseEntity<*>{
        val users = userRepository.findAll()
        return ResponseEntity.ok(users)
    }

    fun setUser(user : User) : ResponseEntity<*>{
        val src = userRepository.save(user)
        return ResponseEntity.ok(src)
    }
}