package com.kolev.stock.app.myapp.controller;

import com.kolev.stock.app.myapp.exceptions.users.UserAlreadyAuthenticatedException;
import com.kolev.stock.app.myapp.exceptions.users.UserDoesNotExistException;
import com.kolev.stock.app.myapp.models.User;
import com.kolev.stock.app.myapp.models.requests.users.UserLoginRequest;
import com.kolev.stock.app.myapp.models.requests.users.UserRegisterRequest;
import com.kolev.stock.app.myapp.models.responses.Response;
import com.kolev.stock.app.myapp.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Map.of;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users/register")
    public ResponseEntity<Response> registerUser(HttpServletRequest servletRequest, @RequestBody UserRegisterRequest request) {

        String authorizationHeader = servletRequest.getHeader("Authorization");

        System.out.println(authorizationHeader);
        // Check if the authorizationHeader is not null and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String bearerToken = authorizationHeader.substring(7); // Extract the token after "Bearer "
            System.out.println(bearerToken);
        }

            try {

            User registeredUser = userService.registerUser(request);

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data(of("user", registeredUser))
                            .message("User registered successfully!")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .success(true)
                            .build()
            );
        }
        catch(UserAlreadyAuthenticatedException err) {

            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.FORBIDDEN)
                            .statusCode(HttpStatus.FORBIDDEN.value())
                            .success(false)
                            .message(err.getMessage())
                            .build()
            );
        }
        catch(RuntimeException err) {

            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(err.getMessage())
                            .success(false)
                            .build()
            );
        }
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<Response> loginUser(@RequestBody UserLoginRequest request) {

        try {

            User loggedInUser = userService.loginUser(request);

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data(of("user", loggedInUser))
                            .message("User successfully logged in!")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        }
        catch(UserAlreadyAuthenticatedException err) {

            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.FORBIDDEN)
                            .statusCode(HttpStatus.FORBIDDEN.value())
                            .success(false)
                            .message(err.getMessage())
                            .build()
            );
        }
        catch(RuntimeException err) {

            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .success(false)
                            .message(err.getMessage())
                            .build()
            );
        }
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Response> getUserById(@PathVariable("id") Long userId) {

        try {

            Optional<User> user = userService.getUserById(userId);

            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("User successfully retrieved")
                            .success(true)
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .data(of("user", user.get()))
                            .build()
            );
        }
        catch (UserDoesNotExistException err) {

            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message(err.getMessage())
                            .success(false)
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
    }

    @GetMapping("/api/users/logout")
    public ResponseEntity<Response> logoutUser() {
        return null;
    }
}
