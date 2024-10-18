package com.comitialsign.First_RESTful_API.controller;

import com.comitialsign.First_RESTful_API.domain.model.User;
import com.comitialsign.First_RESTful_API.service.IUserService;
import jakarta.validation.Valid;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@Description("RESTful API for managing users.")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
        userService.updateUser(user, id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
