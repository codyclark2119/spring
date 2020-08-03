package com.tekcamp.springExercise.Controller;

import com.tekcamp.springExercise.Model.Request.UserRequest;
import com.tekcamp.springExercise.Model.Response.UserResponse;
import com.tekcamp.springExercise.Model.User;
import com.tekcamp.springExercise.Services.UserService;
import com.tekcamp.springExercise.Shared.Dto.UserDto;
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
        //Getting a list of users from the db
        List<UserDto> usersList = userService.getUsers();
        //Creating a list to store the response in
        List<UserResponse> returnList = new ArrayList<>();
        if(usersList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        //Creating a clean response list with no sensitive information and returning it
        usersList.forEach(user -> {
            UserResponse userCopy = new UserResponse();
            BeanUtils.copyProperties(user, userCopy);
            returnList.add(userCopy);
        });
        return ResponseEntity.accepted().body(returnList);
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity getUserById(@PathVariable Long id){
        //Sending the id to search for a valid match
        UserDto foundUser = userService.getUserById(id);
        if(foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        //Creating a clean response with no sensitive information and returning it
        UserResponse userCopy = new UserResponse();
        BeanUtils.copyProperties(foundUser, userCopy);
        return ResponseEntity.accepted().body(userCopy);
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        //Sending the string to search for a valid match
        UserDto foundUser = userService.getUserByEmail(email);
        if(foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        //Creating a clean response with no sensitive information and returning it
        UserResponse userReturn = new UserResponse();
        BeanUtils.copyProperties(foundUser, userReturn);
        return ResponseEntity.accepted().body(userReturn);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserRequest userRequest){
        //Creating an instance to hold user request data
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        //Sending the copied data to the database to be saved and returned
        UserDto createdUser = userService.createUser(userDto);
        if(createdUser == null) {
            return ResponseEntity.badRequest().body("User Already Exists");
        }
        //Creating a clean response with no sensitive information and returning it
        UserResponse userReturn = new UserResponse();
        BeanUtils.copyProperties(createdUser, userReturn);
        return ResponseEntity.accepted().body(userReturn);
    }

    @PutMapping(path = "/{email}")
    public ResponseEntity updateUser(@PathVariable String email, @RequestBody UserRequest userRequest){
        //Creating an instance to hold user request data
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        //Sending the copied updated data to the database to be saved and returned
        UserDto updatedUser = userService.updateUser(email, userDto);
        if(updatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        //Creating a clean response with no sensitive information and returning it
        UserResponse userReturn = new UserResponse();
        BeanUtils.copyProperties(updatedUser, userReturn);
        return ResponseEntity.accepted().body(userReturn);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        //Searching the db for a match and deleting it
        boolean success = userService.deleteUser(id);
        if(!success){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().body("User Deleted");
    }

}
