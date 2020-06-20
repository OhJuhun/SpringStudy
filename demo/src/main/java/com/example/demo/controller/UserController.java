package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
    public String create( UserForm userForm, BindingResult result){
        //valid 이후 BindResult가 있으 오류가 담겨서 실행됨
        if(result.hasErrors()){
            return "users/createUserForm";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setNickname(userForm.getNickname());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        //등록 폼은 Entity
        userService.insertUser(user);
        return "redirect:/";
    }

    @GetMapping
    public String list(Model model){
        List<User> users = userService.getUsers();
        List<UserForm> userForms = new ArrayList<>();
        for(User user :users){
            UserForm userForm = new UserForm(user.getName(),user.getEmail(),user.getNickname(),user.getPassword());
            //생성자를 없앨까? 아니면 그냥 쓸까?
            userForms.add(userForm);
        }
        model.addAttribute("users",userForms);

        return "/users/userList";
    }
}
