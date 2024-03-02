package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.UserRegisterDTO;
import com.vote.onlinevotingsystem.model.entity.User;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    User getAdmin(Long id);

    void deleteUser(Long id);

    boolean changePassword(Long id, String password);
}
