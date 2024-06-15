package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.ProfileUpdateDTO;
import com.vote.onlinevotingsystem.model.dto.UserRegisterDTO;
import com.vote.onlinevotingsystem.model.entity.User;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    User getUserById(Long id);

    void deleteUser(Long id);

    void updateProfile(Long id, ProfileUpdateDTO profileUpdateDTO);
}
