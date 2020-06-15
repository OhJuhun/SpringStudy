package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService; //query

    @GetMapping("/")
    private ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userService.getUsers());
    }
    
    @GetMapping
    private ResponseEntity<Optional<User>> getByName(@RequestParam String name){
        //FindOne으로 하면 안됨
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PostMapping
    private ResponseEntity<String> insert(@RequestBody User user){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try { //unique인 id에 대해 exception 발생 가능성이 존재
            if(user.getCurrent()==null) user.setCurrent(0L);
            userService.insertUser(user);
        }catch (Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("duplicated uid",httpHeaders,HttpStatus.NOT_ACCEPTABLE);
            //이렇게 새로 new 해주는게 좋은 방식인
        }
        return responseEntity;
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
