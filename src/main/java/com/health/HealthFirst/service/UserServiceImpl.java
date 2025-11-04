package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.UserDTO;
import com.health.HealthFirst.dto.UserProfileDTO;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private User toEntity(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }

    private UserDTO toDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public UserDTO signUp(UserDTO userDTO) {
        User user = toEntity(userDTO);
        User savedUser = userRepo.save(user);
        return toDTO(savedUser);
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

    @Override
    public User registerUser(String username, String email, String password) {
        if(userRepo.existsByUsername(username)){
            throw new RuntimeException("Username already exists");
        }
        User user = new User(username, passwordEncoder.encode(password),email);
        return userRepo.save(user);
    }

    public Optional<User> findByUserName(String username){
        return userRepo.findByUsername(username);
    }

    @Override
    public User updateCurrentUserProfile(UserProfileDTO profileDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstname(profileDTO.firstname);
        user.setLastname(profileDTO.lastname);
        user.setAge(profileDTO.age);
        user.setGender(profileDTO.gender);
        if (profileDTO.email != null && !profileDTO.email.isBlank()) {
            user.setEmail(profileDTO.email);
        }
        return userRepo.save(user);
    }
}
