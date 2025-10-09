package com.health.HealthFirst.service;

import com.health.HealthFirst.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    User signUp(User user);
    boolean deleteAccount(int id);
}
