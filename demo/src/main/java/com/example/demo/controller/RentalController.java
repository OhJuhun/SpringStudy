package com.example.demo.controller;

import com.example.demo.dto.RentalForm;
import com.example.demo.dto.RentalSearch;
import com.example.demo.entity.Book;
import com.example.demo.entity.Rental;
import com.example.demo.entity.User;
import com.example.demo.service.BookService;
import com.example.demo.service.RentalService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("rentals")
public class RentalController {
    private final RentalService rentalService;
    private final BookService bookService;
    private final UserService userService;


    //RequiredArgsConstructor
    public RentalController(RentalService rentalService, BookService bookService, UserService userService){
        this.rentalService = rentalService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    public String list(@ModelAttribute("rentalSearch") RentalSearch rentalSearch, Model model){
        List<Rental> rentals = rentalService.findMyRentals(rentalSearch);
        for(Rental rental : rentals){
            System.out.println(rental.getId());
        }
        System.out.println(rentalSearch.getUserName()+" "+rentalSearch.getRentalStatus());
        model.addAttribute("rentals",rentals);
//        model.addAttribute("rentalSearch",rentalSearch);
        return "rentals/rentalList";
    }

    @PostMapping
    public String create(@RequestParam Long userId, @RequestParam Long bookId){
        //save

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("user_id",userId);
        param.put("book_id",bookId);
        //d이렇게 짜면 안된다!!!유지보수가 안좋음!!!!
        System.out.println(userId+ " "+bookId);
        rentalService.insertRental(param);
        return "redirect:/rentals/new";
    }
    @GetMapping("/new")
    public String createForm(Model model){

        List<Book> books = bookService.getBooks();
        List<User> users = userService.getUsers();

        model.addAttribute("books",books);
        model.addAttribute("users",users);
        return "rentals/createRentalForm";
    }


    @PostMapping("/new")
    public String insert(@Validated RentalForm rentalForm, BindingResult result){

        return "redirect:/rentals";
    }

    @PostMapping("/{rentalId}/cancel")
    public String returnRental(@PathVariable("rentalId") Long rentalId){
        //return book method 만들기
        rentalService.returnRental(rentalId);
        return "redirect:/rentals";
    }
}
