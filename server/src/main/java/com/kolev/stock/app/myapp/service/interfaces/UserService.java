package com.kolev.stock.app.myapp.service.interfaces;

import com.kolev.stock.app.myapp.models.User;
import com.kolev.stock.app.myapp.models.requests.UserLoginRequest;
import com.kolev.stock.app.myapp.models.requests.UserRegisterRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User updateUser(User user);
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    boolean userExists(Long userId);
    boolean userExists(String username);
    User registerUser(UserRegisterRequest request);
    User loginUser(UserLoginRequest request);
}
