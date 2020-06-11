package com.example.testproj.testproj.controller

import com.example.testproj.testproj.entity.User
import com.example.testproj.testproj.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val userRepository: UserRepository) {
    @GetMapping()
    fun getUsers(): ResponseEntity<*>{
        val users = userRepository.findAll()
        return ResponseEntity.ok(users)
    }

    @PostMapping()
    fun setUsers(@RequestBody user:User): ResponseEntity<*>{
        val users = userRepository.save(user)
        return ResponseEntity.ok(users)
    }
}