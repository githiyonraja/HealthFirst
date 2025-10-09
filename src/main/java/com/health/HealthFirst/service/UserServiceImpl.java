package com.health.HealthFirst.service;

import com.health.HealthFirst.model.User;
import com.health.HealthFirst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public User signUp(User user) {
        return userRepo.save(user);
    }

    @Override
    public boolean deleteAccount(int id) {
        boolean delete = false;
        try {
            userRepo.deleteById(id);
            System.out.println("Deletion Successful!");
            delete = true;
        }catch(Exception e){
            System.out.println("Invalid User");
        }
        return delete;
    }
}
