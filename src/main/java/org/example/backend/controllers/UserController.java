package org.example.backend.controllers;

import org.example.backend.Classes.User;
import org.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        String username = user.getFirstName() + user.getLastName() ;
        user.setUsername(username);
        return repository.save(user);
    }

    @GetMapping("/loginCheck")
    public String getUsers(@RequestBody User user) {
        User u = repository.findByUsername(user.getUsername());
    }
}
