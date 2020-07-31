package com.tekcamp.springExercise.Services.Implementations;

import com.tekcamp.springExercise.Model.User;
import com.tekcamp.springExercise.Services.UserService;
import com.tekcamp.springExercise.Repositories.UserRepository;
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
    public List<User> getUsers() {
        List<User> userList = new ArrayList<User>();

        userList = (List<User>) userRepository.findAll();

        return userList;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User foundUser = userRepository.findByEmail(email);
        return foundUser;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> returnedUser = userRepository.findById(id);
        if(!returnedUser.isEmpty()){
            User foundUser = returnedUser.get();
            return foundUser;
        }
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        if(userRepository.existsById(id)){
            User returnedUser = userRepository.findById(id).get();
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
            return userRepository.save(returnedUser);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
