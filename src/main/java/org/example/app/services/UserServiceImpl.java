package org.example.app.services;

import org.example.app.exceptions.UserException;
import org.example.app.domain.User;

import java.util.Collection;
import java.util.HashMap;

public class UserServiceImpl implements UserService {
    private final HashMap<Long, User> map;

    public UserServiceImpl() {
        map = new HashMap<>();
    }

    @Override
    public void addUser(User user) {
        map.put(user.getId(), user);
    }

    @Override
    public Collection<User> getUsers() {
        return map.values();
    }

    @Override
    public User getUser(Long id) {
        return map.get(id);
    }

    @Override
    public User editUser(User user) throws UserException {
        try {
            if (user.getId() == null)
                throw new UserException("ID required");
            User userEdit = map.get(user.getId());

            if (userEdit == null)
                throw new UserException("User not found");

            if (user.getId() != null) {
                userEdit.setId(user.getId());
            }

            if (user.getName() != null) {
                userEdit.setName(user.getName());
            }

            if (user.getPhone() != null) {
                userEdit.setPhone(user.getPhone());
            }
            if (user.getEmail() != null) {
                userEdit.setEmail(user.getEmail());
            }

            return user;
        } catch (Exception ex) {
            throw new UserException(ex.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        map.remove(id);
    }
}

