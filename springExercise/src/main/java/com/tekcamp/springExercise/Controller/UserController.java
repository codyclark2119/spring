package com.tekcamp.springExercise.Controller;

import com.tekcamp.springExercise.Model.User;
import com.tekcamp.springExercise.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        List<User> usersList = userService.getUsers();
        return usersList;
    }

    @PostMapping
    public void getUser(@RequestBody User user){
        userService.createUser(user);
    }

    @PutMapping
    public void updateUser(){

    }

    @DeleteMapping
    public void deleteUser(){

    }

}
