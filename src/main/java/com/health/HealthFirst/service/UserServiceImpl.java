package com.health.HealthFirst.service;

import com.health.HealthFirst.dto.UserDTO;
import com.health.HealthFirst.model.User;
import com.health.HealthFirst.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    private User toEntity(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }

    private UserDTO toDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
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
}
