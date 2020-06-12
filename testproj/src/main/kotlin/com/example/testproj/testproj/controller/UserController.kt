package com.example.testproj.testproj.controller

import com.example.testproj.testproj.entity.User
import com.example.testproj.testproj.repository.UserRepository
import com.example.testproj.testproj.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {
    @GetMapping()
    fun getUsers(): ResponseEntity<*>{
        val users = userService.getUser()
        return ResponseEntity.ok(users)
    }

    @PostMapping()
    fun setUsers(@RequestBody user:User): ResponseEntity<*>{
        val users = userService.setUser(user)
        return ResponseEntity.ok(users)
    }
}