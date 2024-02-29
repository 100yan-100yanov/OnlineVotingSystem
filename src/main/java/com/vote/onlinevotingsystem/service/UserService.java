package com.vote.onlinevotingsystem.service;

import com.vote.onlinevotingsystem.model.dto.UserRegisterDTO;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);
}
