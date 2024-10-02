package com.login.loginDashboard.service;

import com.login.loginDashboard.entity.User;
import com.login.loginDashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser (User user)throws Exception {
        Optional<User> exsistingUser = userRepository.findByUsername(user.getUsername());
        if (exsistingUser.isPresent()){
            throw new Exception("user already exist");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User Authenticate(String username,String password) throws Exception {
        Optional<User> Useropt = userRepository.findByUsername(username);
        if (Useropt.isPresent()) {
            User user = Useropt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new Exception("Invalid password");
            }
        } else {
                throw new Exception("User Not Found");
        }
    }
}
