package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    } //문제 없음

    public Optional<User> getUserByName(String name){
        return userRepository.findByName(name);
    }

    public void insertUser(User user){
        userRepository.save(user); //문제 없음
    }

    @Transactional
    public void deleteUser(Long uid){
        //TO DO delete User by uid
    }

    @Transactional
    public void modifyUser(User user){
        //TO DO modify User by user info
    }

}
