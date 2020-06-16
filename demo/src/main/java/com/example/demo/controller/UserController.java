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
    private ResponseEntity<List<User>> getByName(@RequestParam String name){
        //한 사람이어도 여러개의 아이디가 있을 수 있음
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PostMapping
    private ResponseEntity<String> insert(@RequestBody User user){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try { //unique인 id에 대해 exception 발생 가능성이 존재
            userService.insertUser(user);
        }catch (Exception e){
            //다양한 에러를 잡아주기 위해서는 다양한 Exception이 필요한데..
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("duplicated uid",httpHeaders,HttpStatus.NOT_ACCEPTABLE);
            //이렇게 새로 new 해주는게 좋은 방식인가
        }
        return responseEntity;
    }

    @PutMapping
    private ResponseEntity<String> modify(@RequestBody User user){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try {
            userService.modifyUser(user);
        }catch (Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("not found",httpHeaders,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping
    private ResponseEntity<String> delete(@RequestParam Long uid){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try {
            userService.deleteUser(uid);
        }catch (Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("not found",httpHeaders,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
    
    
}
