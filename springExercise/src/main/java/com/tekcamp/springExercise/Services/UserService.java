package com.tekcamp.springExercise.Services;

import com.tekcamp.springExercise.Shared.Dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserByEmail(String email);

    UserDto getUserById(Long id);

    UserDto updateUser(String id, UserDto user);

    boolean deleteUser(Long id);

    List<UserDto> getUsers(int page, int limit);
}
