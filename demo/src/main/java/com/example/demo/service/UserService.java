package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    } //문제 없음

    public List<User> getUserByName(String name){
        return userRepository.findByName(name);
    }

    public Optional<User> getUserByNickname(String nickname){ return userRepository.findByNickname(nickname);}
    public void insertUser(User user){
        userRepository.save(user); //문제 없음
    }

    @Transactional
    public void deleteUser(Long uid){
        //TODO delete User by uid
    }

    @Transactional
    public void modifyUser(User user){
        //TODO modify User by user info
    }

    public User modifyEmail(User user) {

        return userRepository.save(user);
    }

    public User modifyPassword(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
