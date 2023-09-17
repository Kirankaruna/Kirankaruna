package com.example.todo.service;

import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean doesEmailExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
    public void addUser(User user){
       userRepository.save(user);
    }
    public List<User> viewUsers() {
        return userRepository.findAll();
    }

    public String deleteUser(String userId) {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return "User deleted successfully";
        }
        return "User with "+userId+" doesn't exist";
    }
}
