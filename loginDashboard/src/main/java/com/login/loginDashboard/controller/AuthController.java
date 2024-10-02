package com.login.loginDashboard.controller;

import com.login.loginDashboard.entity.User;
import com.login.loginDashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        try{
            User registeredUser= userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        try{
            User authenticatedUser= userService.Authenticate(user.getUsername(),user.getPassword());
            return ResponseEntity.ok("User login successfully!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
