package com.tekcamp.springExercise.Services;

import com.tekcamp.springExercise.Model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User createUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long id);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
