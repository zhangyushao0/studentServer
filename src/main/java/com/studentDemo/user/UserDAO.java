package com.studentDemo.user;

import java.util.List;

public interface UserDAO {
    User getUserById(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);

    void createUser(User user);
}
