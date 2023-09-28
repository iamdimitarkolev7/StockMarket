package com.kolev.stock.app.myapp.service.implementations;

import com.kolev.stock.app.myapp.exceptions.users.PasswordsDoNotMatchException;
import com.kolev.stock.app.myapp.exceptions.users.UserAlreadyExistsException;
import com.kolev.stock.app.myapp.exceptions.users.UserDoesNotExistException;
import com.kolev.stock.app.myapp.models.*;
import com.kolev.stock.app.myapp.models.requests.users.UserLoginRequest;
import com.kolev.stock.app.myapp.models.requests.users.UserRegisterRequest;
import com.kolev.stock.app.myapp.repository.RoleRepository;
import com.kolev.stock.app.myapp.repository.UserRepository;
import com.kolev.stock.app.myapp.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(createRoleIfNotFound("ROLE_USER"));
        user.setRoles(roles);

        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    private Role createRoleIfNotFound(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = roleRepository.save(new Role(roleName));
        }

        return role;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {

        if (!userExists(userId)) {
            throw new UserDoesNotExistException("User with such id does not exist!");
        }

        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User registerUser(UserRegisterRequest request) {

        boolean userExists = userExists(request.getUsername());

        if (userExists) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException("Passwords do not match!");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(request.getPassword())
                .userWallet(new Wallet())
                .portfolio(new Portfolio())
                .build();

        return createUser(user);
    }

    @Override
    public User loginUser(UserLoginRequest request) {

        Optional<User> user = getUserByUsername(request.getUsername());

        if (user.isEmpty()) {
            throw new UserDoesNotExistException("User does not exist!");
        }

        boolean passwordsMatch = bCryptPasswordEncoder.matches(request.getPassword(), user.get().getPassword());

        if (!passwordsMatch) {
            throw new PasswordsDoNotMatchException("Passwords do not match!");
        }

        return user.get();
    }
}
