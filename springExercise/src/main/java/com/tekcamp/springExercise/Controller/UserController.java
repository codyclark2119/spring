package com.tekcamp.springExercise.Controller;

import com.tekcamp.springExercise.Model.User;
import com.tekcamp.springExercise.Services.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getUsers(){
        List<User> usersList = userService.getUsers();
        return ResponseEntity.accepted().body(usersList);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        User foundUser = userService.getUserById(id);
        return ResponseEntity.accepted().body(foundUser);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        User foundUser = userService.getUserByEmail(email);
        return ResponseEntity.accepted().body(foundUser);
    }

    @PostMapping
    public ResponseEntity getUser(@RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.accepted().body("User Created");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id, user);
        return ResponseEntity.accepted().body("User Updated at Id: " + id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.accepted().body("User Deleted at Id: " + id);
    }

}
