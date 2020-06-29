package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getUsers(){

        return userRepository.findAll();
    } //문제 없음

    public List<User> getUserByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> getUserByNickname(String nickname){ return userRepository.findByNickname(nickname);}

    @Transactional
    public void insertUser(User user){
        userRepository.save(user); //문제 없음
    }

    @Transactional
    public User modifyUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
