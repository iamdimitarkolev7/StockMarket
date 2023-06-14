package com.kolev.stock.app.myapp.controller;

import com.kolev.stock.app.myapp.exceptions.users.UserAlreadyAuthenticatedException;
import com.kolev.stock.app.myapp.models.User;
import com.kolev.stock.app.myapp.models.requests.users.UserLoginRequest;
import com.kolev.stock.app.myapp.models.requests.users.UserRegisterRequest;
import com.kolev.stock.app.myapp.models.responses.Response;
import com.kolev.stock.app.myapp.service.interfaces.UserService;
import com.kolev.stock.app.myapp.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static java.util.Map.of;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private final JwtUtil jwtUtil;

    @PostMapping("/api/users/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserRegisterRequest request) {

        try {

            User registeredUser = userService.registerUser(request);

            return ResponseEntity.ok(
                    Response.builder().timeStamp(LocalDateTime.now())
                            .data(of("createdUser", registeredUser))
                            .message("User registered successfully!")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build()
            );
        }
        catch(UserAlreadyAuthenticatedException err) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.FORBIDDEN)
                            .statusCode(HttpStatus.FORBIDDEN.value())
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
                            .data(of("loggedInUser", loggedInUser))
                            .message("User successfully logged in!")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build()
            );
        }
        catch(UserAlreadyAuthenticatedException err) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.FORBIDDEN)
                            .statusCode(HttpStatus.FORBIDDEN.value())
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
                            .build()
            );
        }
    }

    @GetMapping("/api/users/logout")
    public ResponseEntity<Response> logoutUser() {
        return null;
    }
}
