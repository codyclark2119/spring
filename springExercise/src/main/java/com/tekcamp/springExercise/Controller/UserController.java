package com.tekcamp.springExercise.Controller;

import com.tekcamp.springExercise.Model.Response.UserResponse;
import com.tekcamp.springExercise.Model.User;
import com.tekcamp.springExercise.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUsers(){
        List<User> usersList = userService.getUsers();
        List<UserResponse> returnList = new ArrayList<>();
        usersList.forEach(user -> {
            UserResponse userCopy = new UserResponse();
            BeanUtils.copyProperties(user, userCopy);
            returnList.add(userCopy);
        });
        return ResponseEntity.accepted().body(returnList);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        User foundUser = userService.getUserById(id);
        UserResponse userCopy = new UserResponse();
        BeanUtils.copyProperties(foundUser, userCopy);
        return ResponseEntity.accepted().body(userCopy);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        User foundUser = userService.getUserByEmail(email);
        UserResponse userCopy = new UserResponse();
        BeanUtils.copyProperties(foundUser, userCopy);
        return ResponseEntity.accepted().body(userCopy);
    }

    @PostMapping
    public ResponseEntity getUser(@RequestBody User user){
        User createdUser = userService.createUser(user);
        UserResponse userCopy = new UserResponse();
        BeanUtils.copyProperties(createdUser, userCopy);
        return ResponseEntity.accepted().body(userCopy);
    }

    @PutMapping(path = "/{email}")
    public ResponseEntity updateUser(@PathVariable String email, @RequestBody User user){
        User updatedUser = userService.updateUser(email, user);
        UserResponse userCopy = new UserResponse();
        BeanUtils.copyProperties(updatedUser, userCopy);
        return ResponseEntity.accepted().body(userCopy);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.accepted().body("User Deleted");
    }

}
