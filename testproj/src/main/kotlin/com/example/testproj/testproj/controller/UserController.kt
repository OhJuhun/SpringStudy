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
    fun getUsers(): ResponseEntity<*>{ //모든 유저 조회
        val users = userService.getUsers()
        return ResponseEntity.ok(users)
    }
    @PostMapping() //유저 정보
    fun setUsers(@RequestBody user:User): ResponseEntity<*>{
        val users = userService.setUser(user)
        if(users!="200") return ResponseEntity.ok(users) //이건 좀 이상해
        return ResponseEntity.ok(users)
    }

    @GetMapping("/findnamebyuid")
    fun getUser(@RequestParam uid: String): ResponseEntity<*>{
        val userName = userService.getUserNameByUId(uid)
        return ResponseEntity.ok(userName)
    }
}