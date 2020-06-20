package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("users")
@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("userForm",new UserForm());
        return "users/createUserForm";
    }

    @PostMapping("/new")
    public String create(@Valid UserForm userForm, BindingResult result){
        //valid 이후 BindResult가 있으 오류가 담겨서 실행됨
        if(result.hasErrors()){
            return "users/createUserForm";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setNickname(userForm.getNickname());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());

        userService.insertUser(user);
        return "redirect:/";
    }

}
