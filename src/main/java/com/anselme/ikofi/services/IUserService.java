package com.anselme.ikofi.services;

import java.util.Optional;
import com.anselme.ikofi.models.User;

public interface IUserService {

    public User create(User user);

    public User findById(long id);

    public Optional<User> findUser(String login);

    public boolean emailAlreadyInUse(String email);

    public boolean userNameAlreadyInUse(String username);

    public User getLoggedInUser();
}
