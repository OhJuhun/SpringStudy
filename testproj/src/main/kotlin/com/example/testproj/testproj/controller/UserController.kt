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
        val users = userService.getUsers()
        return ResponseEntity.ok(users)
    }

    @GetMapping("/findbyid")
    fun getUserById(id: Long): ResponseEntity<*>{
        val user = userService.getUserById(id)
        println("Controller" + user.toString())
        return ResponseEntity.ok(user)
    }
    @PostMapping()
    fun setUsers(@RequestBody user:User): ResponseEntity<*>{
        val users = userService.setUser(user)
        return ResponseEntity.ok(users)
    }
}