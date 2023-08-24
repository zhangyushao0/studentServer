package com.studentDemo.user;

import java.util.List;

public interface UserDAO {
    User getUser(Long id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(Long id);
}
