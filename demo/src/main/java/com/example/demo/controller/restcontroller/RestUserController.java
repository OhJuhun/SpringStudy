package com.example.demo.controller.restcontroller;


import com.example.demo.entity.User;
import com.example.demo.exception.NotCorrectException;
import com.example.demo.exception.NotFoundException;
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
public class RestUserController {
    @Autowired
    private UserService userService; //query
}
