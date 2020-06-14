package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService; //query

    @GetMapping
    private ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getUsers());
    }
    
    @GetMapping("/getByName")
    private ResponseEntity<Optional<User>> getByName(@RequestParam String name){
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PostMapping
    private void insert(@RequestBody User user){
        userService.insertUser(user);
    }

    @PutMapping
    private void modify(@RequestBody User user){
        userService.modifyUser(user);
    }

    @DeleteMapping
    private void delete(@RequestParam Long uid){
        userService.deleteUser(uid);
    }
    
    
}
