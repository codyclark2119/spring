package com.tekcamp.springExercise.Services;

import com.tekcamp.springExercise.Model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void createUser(User user);

    User getUserByEmail(String email);
}
