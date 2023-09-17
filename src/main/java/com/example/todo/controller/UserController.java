package com.example.todo.controller;

import com.example.todo.model.User;
import com.example.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        boolean emailExists = userService.doesEmailExist(user.getEmail());

        if (emailExists) {
            return ResponseEntity.badRequest().body("Email already exists.");
        } else {
            userService.addUser(user);
            return ResponseEntity.ok("User added successfully.");
        }
    }

    @GetMapping("/viewAll")
    public List<User> viewUsers(){
        return  userService.viewUsers();
    }
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }

}
