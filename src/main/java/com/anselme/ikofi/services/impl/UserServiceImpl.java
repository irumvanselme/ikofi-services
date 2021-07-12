package com.anselme.ikofi.services.impl;


import com.anselme.ikofi.models.User;
import com.anselme.ikofi.repositories.IUserRepository;
import com.anselme.ikofi.services.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Optional<User> findUser(String login) {
        return userRepository.findByEmailOrUsername(login, login);
    }

    public boolean emailAlreadyInUse(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean userNameAlreadyInUse(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getLoggedInUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found ... "));
    }
}
