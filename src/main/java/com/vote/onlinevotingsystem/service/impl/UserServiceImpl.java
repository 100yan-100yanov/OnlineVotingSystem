package com.vote.onlinevotingsystem.service.impl;

import com.vote.onlinevotingsystem.model.dto.ProfileUpdateDTO;
import com.vote.onlinevotingsystem.model.dto.UserRegisterDTO;
import com.vote.onlinevotingsystem.model.entity.Role;
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
        return getUserById(id);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);

        userRepository.delete(user);
    }


    @Override
    public boolean changePassword(Long id, String password) {

        //TODO
        return false;
    }

    @Override
    public void updateProfile(Long id, ProfileUpdateDTO profileUpdateDTO) {
        User user = getUserById(id);

        user.setUsername(profileUpdateDTO.getUsername());
        user.setFirstName(profileUpdateDTO.getFirstName());
        user.setLastName(profileUpdateDTO.getLastName());

        userRepository.save(user);
        //TODO Change it to RESTful
    }

    private User mapToUser(UserRegisterDTO userRegisterDTO) {
        User user = new User();

        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setRoles(List.of(new Role().setType(RoleType.VOTER)));

        return user;
    }

    private boolean isRegistered(UserRegisterDTO userRegisterDTO) {
        return userRepository.findByUsername(userRegisterDTO.getUsername()).isPresent() ||
                userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent();
    }

    private User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with id " + id + " does not exist!");
        }

        return user.get();
    }
}
