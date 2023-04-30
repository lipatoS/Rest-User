package org.example.app.services;

import org.example.app.domain.User;
import org.example.app.exceptions.UserException;

import java.util.Collection;

public interface UserService {
    void addUser(User user);

    Collection<User> getUsers();

    User getUser(Long id);

    User editUser(User user) throws UserException;

    void deleteUser(Long id);
}
