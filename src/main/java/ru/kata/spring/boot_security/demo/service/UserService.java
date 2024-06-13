package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();
    void saveUser(User user);
    void deleteUser(Long userId);
    Optional<User> getUserById(Long userId);
}
