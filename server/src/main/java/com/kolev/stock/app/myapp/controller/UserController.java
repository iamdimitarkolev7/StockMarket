package com.kolev.stock.app.myapp.controller;

import com.kolev.stock.app.myapp.exceptions.users.UserAlreadyAuthenticatedException;
import com.kolev.stock.app.myapp.exceptions.users.UserDoesNotExistException;
import com.kolev.stock.app.myapp.models.User;
import com.kolev.stock.app.myapp.models.requests.users.UserLoginRequest;
import com.kolev.stock.app.myapp.models.requests.users.UserRegisterRequest;
import com.kolev.stock.app.myapp.models.responses.Response;
import com.kolev.stock.app.myapp.service.interfaces.UserService;
import com.kolev.stock.app.myapp.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/api/users/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserRegisterRequest request) {

        try {

            User registeredUser = userService.registerUser(request);
            String token = jwtService.generateToken(
                    registeredUser.getUsername(),
                    registeredUser.getUserId(),
                    registeredUser.getRoles());

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data("user", registeredUser)
                            .data("jwtToken", token)
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
            String token = jwtService.generateToken(
                    loggedInUser.getUsername(),
                    loggedInUser.getUserId(),
                    loggedInUser.getRoles());

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data("user", loggedInUser)
                            .data("jwtToken", token)
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
    public ResponseEntity<Response> getUserById(@PathVariable("id") Long userId,
                                                @RequestHeader("Authorization") String token) {

        try {

            Optional<User> user = userService.getUserById(userId);

            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("User successfully retrieved!")
                            .success(true)
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .data("user", user.get())
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
    public ResponseEntity<Response> logoutUser(@RequestHeader("Authorization") String token) {

        try {

            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("User successfully logged out!")
                            .success(true)
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        }
        catch (Exception err) {
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
}
