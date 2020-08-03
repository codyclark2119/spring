package com.tekcamp.springExercise.Services.Implementations;

import com.tekcamp.springExercise.Model.User;
import com.tekcamp.springExercise.Services.UserService;
import com.tekcamp.springExercise.Repositories.UserRepository;
import com.tekcamp.springExercise.Shared.Dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        //Gets all users from userRepository and casts it to a list of users
        List<User> userList = (List<User>) userRepository.findAll();
        //Creates a Dto returnList to only return POJOs
        List<UserDto> returnList = new ArrayList<>();
        //Loop through users from database
        userList.forEach(user -> {
            //Create a userDto for each user and add to returnList
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            returnList.add(userDto);
        });
        //Send back POJO list of user values
        return returnList;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        //Check if user already exists with the provided email
        if((userRepository.findByEmail(userDto.getEmail()) == null)){
            //Creates a new user for database entry and gives it the values from frontend via dto
            User user = new User();
            BeanUtils.copyProperties(userDto, user);
            //Save user to database and return saved user information to front end
            User savedUser = userRepository.save(user);
            UserDto returnDto = new UserDto();
            BeanUtils.copyProperties(savedUser, returnDto);
            return returnDto;
        } else {
            //returning null for error handling
            return null;
        }
    }

    @Override
    public UserDto getUserByEmail(String email) {
        //Custom UserRepository method to return user by string email passed
        User foundUser = userRepository.findByEmail(email);
        //returning null for error handling of no user found
        if(foundUser == null){
            return null;
        } else {
            UserDto returnDto = new UserDto();
            BeanUtils.copyProperties(foundUser, returnDto);
            return returnDto;
        }
    }

    @Override
    public UserDto getUserById(Long id) {
        User foundUser = userRepository.findById(id).get();
        //returning null for error handling of unfound user
        if(foundUser == null){
            return null;
        } else {
            UserDto returnDto = new UserDto();
            BeanUtils.copyProperties(foundUser, returnDto);
            return returnDto;
        }
    }

    @Override
    public UserDto updateUser(String email, UserDto user) {
        User returnedUser = userRepository.findByEmail(email);
        //returning null for error handling of unfound user
        if(returnedUser == null){
            return null;
        }
        if(!user.getFirstName().isEmpty()){
            returnedUser.setFirstName(user.getFirstName());
        }
        if(!user.getLastName().isEmpty()){
            returnedUser.setLastName(user.getLastName());
        }
        if(!user.getEmail().isEmpty()){
            returnedUser.setEmail(user.getEmail());
        }
        if(!user.getPassword().isEmpty()){
            returnedUser.setPassword(user.getPassword());
        }
        User updatedUser = userRepository.save(returnedUser);
        UserDto returnDto = new UserDto();
        BeanUtils.copyProperties(updatedUser, returnDto);
        return returnDto;
    }

    @Override
    public boolean deleteUser(Long id) {
        //If user is found they are removed from database and returns successful
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
