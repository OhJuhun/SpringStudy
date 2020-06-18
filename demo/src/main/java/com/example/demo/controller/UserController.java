package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.oops.ExceptionTableElement;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService; //query

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> result = userService.getUsers();
        System.out.println(result);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/getByName")
    public ResponseEntity<List<User>> getByName(@RequestParam String name){
        //한 사람이어도 여러개의 아이디가 있을 수 있음
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody User user){ //회원가입
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try {
            //unique인 id에 대해 exception 발생 가능성이 존재
            userService.insertUser(user);
        }catch (Exception e){
            //다양한 에러를 잡아주기 위해서는 다양한 Exception이 필요한데..
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("duplicated",httpHeaders,HttpStatus.NOT_ACCEPTABLE);
            //이렇게 새로 new 해주는게 좋은 방식인가
        }
        return responseEntity;
    }

    @PatchMapping("/modifyEmail")
    public ResponseEntity<User> modifyEmail(@RequestParam(name="nickname") String nickname,
                                          @RequestParam(name="password") String password,
                                          @RequestParam(name="newEmail") String newEmail){
        ResponseEntity<User> responseEntity = new ResponseEntity<User>(HttpStatus.OK);
        try{
            Optional<User> user = userService.getUserByNickname(nickname); //유저까지 넣을 경우 맞아도 다시 조회해야함

            if(user.get()==null){
                throw new Exception("User Not Found");
            }
            //matched user
            if(!checkCorrectPassword(password,user.get().getPassword())) {
                throw new Exception("Password Not Correct");
            }

            user.get().setEmail(newEmail);
            responseEntity = ResponseEntity.ok(userService.modifyEmail(user.get()));

        }catch (Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<User>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return responseEntity;
    }

    @PatchMapping("/modifyPassword")
    public ResponseEntity<User> modifyPassword(@RequestParam(name="nickname") String nickname,
                                               @RequestParam(name="password") String password,
                                               @RequestParam(name="newEmail") String newPassword){
        //겹치는 로직 존재
        ResponseEntity<User> responseEntity = new ResponseEntity<User>(HttpStatus.OK);
        try{
            Optional<User> user = userService.getUserByNickname(nickname);

            if(user.get()==null){ //존재하지 않는 아이디
                throw new Exception("User Not Found");
            }

            if(!checkCorrectPassword(user.get().getPassword(),password)){ //비밀번호가 틀림
                throw new Exception("Password Not Correct");
            }

            user.get().setPassword(newPassword);
            responseEntity = ResponseEntity.ok(userService.modifyPassword(user.get()));

        } catch(Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<User>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return responseEntity;
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long uid){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        try {
            userService.deleteUser(uid);
        }catch (Exception e){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(e.toString(),null);
            responseEntity = new ResponseEntity<String>("not found",httpHeaders,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


    private Boolean checkCorrectPassword(String inPassword, String password){
        System.out.println(inPassword+" "+password);
        System.out.println("checkCorrectPassword = "+inPassword.equals(password));
        return inPassword.equals(password);
    }
    
}
