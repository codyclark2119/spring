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

    @GetMapping(path = "/id/{id}")
    public User getUserById(@PathVariable Long id){
        User foundUser = userService.getUserById(id);
        return foundUser;
    }

    @GetMapping(path = "/{email}")
    public User getUserByEmail(@PathVariable String email){
        User foundUser = userService.getUserByEmail(email);
        return foundUser;
    }

    @PostMapping
    public String getUser(@RequestBody User user){
        userService.createUser(user);
        return "User Created";
    }

    @PutMapping(path = "/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id, user);
        return "User Updated at Id: " + id;
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User Deleted at Id: " + id;
    }

}
