package com.vote.onlinevotingsystem.service.impl;

import com.vote.onlinevotingsystem.model.entity.Role;
import com.vote.onlinevotingsystem.model.entity.User;
import com.vote.onlinevotingsystem.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    private final  UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(CustomUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private static UserDetails map(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles()
                        .stream()
                        .map(CustomUserDetailsService::grantAuthority)
                        .toList())
                .build();
    }

    private static GrantedAuthority grantAuthority(Role role) {
        return new SimpleGrantedAuthority("ROLE_" + role.getType());
    }
}
