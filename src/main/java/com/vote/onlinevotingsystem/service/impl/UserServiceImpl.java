package com.vote.onlinevotingsystem.service.impl;

import com.vote.onlinevotingsystem.model.dto.UserRegisterDTO;
import com.vote.onlinevotingsystem.model.entity.User;
import com.vote.onlinevotingsystem.model.enums.UserRole;
import com.vote.onlinevotingsystem.repository.UserRepository;
import com.vote.onlinevotingsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {

        if (isRegistered(userRegisterDTO)) {
            throw new IllegalArgumentException("Username/Email is already taken.");
        }

        User user = mapToUser(userRegisterDTO);
        userRepository.save(user);
    }

    private User mapToUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setRole(List.of(UserRole.VOTER));

        return user;
    }

    private boolean isRegistered(UserRegisterDTO userRegisterDTO) {
        return userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent() ||
                userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent();
    }
}
