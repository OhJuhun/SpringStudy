package com.example.demo.dto;


public class UserForm {

    private String name;
    private String email;
    private String nickname;
    private String password;

    public UserForm(){

    }
    public UserForm(String name, String email, String nickname, String password){
        this.name=name;
        this.email=email;
        this.nickname=nickname;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
