package com.example.demo.controller;

import com.example.demo.dto.RentalForm;
import com.example.demo.entity.Rental;
import com.example.demo.entity.RentalSearch;
import com.example.demo.service.BookService;
import com.example.demo.service.RentalService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("rentals")
public class RentalController {
    private final RentalService rentalService;
    private final BookService bookService;
    private final UserService userService;

    public RentalController(RentalService rentalService, BookService bookService, UserService userService){
        this.rentalService = rentalService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("rentalForm",new RentalForm());
        return "rentals/createRentalForm";
    }
    @GetMapping //LIST ALL
    public String list(Model model){
        List<Rental> rentals = rentalService.getRentals();

        return "rentals/rentalList";
    }
//
//    @PostMapping("/new")
//    public String insert(@Validated RentalForm rentalForm, BindingResult result){
//        return "redirect:rentals";
//    }
}
