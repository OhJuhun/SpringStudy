package com.example.demo.controller.restcontroller;

import com.example.demo.entity.Book;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class RestBookController {
    @Autowired
    BookService bookService;

}
