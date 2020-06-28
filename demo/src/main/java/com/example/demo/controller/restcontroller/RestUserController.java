package com.example.demo.controller.restcontroller;


import com.example.demo.entity.User;
import com.example.demo.exception.NotCorrectException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class RestUserController {
    @Autowired
    private UserService userService; //query

    /*
     * TODO : CREATE NEW USER INFO
     *  Created 201, Processed but not created 200, No Result to Return 204
     *  Invalid Data 400
     */
    @PostMapping
    public ResponseEntity createUser(@RequestBody User user){
        try{
            userService.insertUser(user);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /*
     * TODO : GET ALL USERS
     *  Return 200
     *  Not Found 404
     */
    @GetMapping
    public ResponseEntity getAllUsers(){
        List<User> users = userService.getUsers();
        if(users.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(users,HttpStatus.OK);
    }

    /*
     * TODO : GET USER BY NAME
     *  Return 200
     *  Not Found 404
     */
    @GetMapping("/name/{name}")
    public ResponseEntity getUsersByName(@PathVariable("name") String name){
        List<User> users = userService.getUserByName(name);
        if(users.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(users,HttpStatus.OK);
    }

    /*
     * TODO : GET USER BY NICKNAME
     *  Return 200
     *  Not Found 404
     */

    @GetMapping("/nickname/{nickname}")
    public ResponseEntity getUserByNickname(@PathVariable("nickname") String nickname){
        Optional<User> user = userService.getUserByNickname(nickname);

        return user.map(response ->
            new ResponseEntity(response,HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
        //Optional With ResponseEntity
    }

    /*
     * TODO : MODIFY USER INFO
     *  Created 201, Updated 200 or 201, No Result to Return 204
     *  Invalid Data 400, Conflict 409
     */
    @PutMapping
    public ResponseEntity modifyUser(@RequestBody User user){
        try{
            userService.modifyUser(user);
        }
        catch(Exception e){ //CONFLICT OR NOT FOUND 구분 어케하지
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /*
     * TODO : DELETE USER
     *  RETURN 202
     *  Not Found 404
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        try{
            userService.deleteUser(id);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
