package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.UserDTO;
import com.health.HealthFirst.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    UserDTO signUp(UserDTO userDTO);
    boolean deleteAccount(int id);
}
