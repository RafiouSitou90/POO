package com.rafdev.prova.blog.api.repository;

import com.rafdev.prova.blog.api.database.Database;
import com.rafdev.prova.blog.api.entity.User;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {

    private final List<User> users;

    public UserRepository(Database database) {
        this.users = database.getUsers();
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        users.add(user);

        return user;
    }

    public User update(User user) {
        User userBD = findById(user.getId());

        if (userBD != null) {
            users.set(users.indexOf(userBD), user);
        }

        return user;
    }

    public void delete(Long id) {
        users.removeIf(user -> Objects.equals(user.getId(), id));
    }

    public User findById(Long id) {
        for (User user: users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    public User findByUsername(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }

    public User findByEmail(String email) {
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }

    public User findByToken(String token) {
        for (User user: users) {
            if (user.getToken() != null && user.getToken().equals(token)) {
                return user;
            }
        }

        return null;
    }

    public boolean existsByUsername(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public boolean existsByEmail(String email) {
        for (User user: users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    public boolean existsById(Long id) {
        for (User user: users) {
            if (user.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
