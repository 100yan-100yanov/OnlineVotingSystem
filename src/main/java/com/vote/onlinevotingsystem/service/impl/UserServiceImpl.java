package com.vote.onlinevotingsystem.service.impl;

import com.vote.onlinevotingsystem.model.dto.UserRegisterDTO;
import com.vote.onlinevotingsystem.model.entity.User;
import com.vote.onlinevotingsystem.model.enums.RoleType;
import com.vote.onlinevotingsystem.repository.UserRepository;
import com.vote.onlinevotingsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public User getAdmin(Long id) {
        Optional<User> admin = userRepository.findById(id);

        if (admin.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " does not exist!");
        }
        return admin.get();
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " does not exist!");
        }
        userRepository.delete(user.get());
    }

    @Override
    public boolean changePassword(Long id, String password) {

        //TODO
        return false;
    }

    private User mapToUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setRole(List.of(RoleType.VOTER));

        return user;
    }

    private boolean isRegistered(UserRegisterDTO userRegisterDTO) {
        return userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent() ||
                userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent();
    }
}
