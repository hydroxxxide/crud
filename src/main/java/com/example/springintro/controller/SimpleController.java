package com.example.springintro.controller;

import com.example.springintro.entity.User;
import com.example.springintro.repository.UserRepository;
import com.example.springintro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimpleController {
    @Autowired
    UserService service;
    @Autowired
    UserRepository userRepository;


        @GetMapping("/")
        public List<User> getUsers() {
            return userRepository.findAll();
        }

        // Получить информацию о пользователе по ID
        @GetMapping("/get{id}")
        public User getUser(@PathVariable Long id) {
            return userRepository.findById(id).orElseThrow();
        }

        // Создать нового пользователя
        @PostMapping("/create")
        public User createUser(@Validated @RequestBody User user) {
            return userRepository.save(user);
        }

        // Обновить информацию о пользователе по ID
        @PutMapping("/update{id}")
        public User updateUser(@PathVariable Long id, @Validated @RequestBody User userUpdated) throws Exception {
            return userRepository.findById(id)
                    .map(user -> {
                        user.setUsername(userUpdated.getUsername());
                        user.setEmail(userUpdated.getEmail());
                        return userRepository.save(user);
                    })
                    .orElseThrow(() -> new Exception("User not found"));
        }

        // Удалить пользователя по ID
        @DeleteMapping("/delete{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
            return userRepository.findById(id)
                    .map(user -> {
                        userRepository.delete(user);
                        return ResponseEntity.ok().build();
                    })
                    .orElseThrow(() -> new Exception("User not found"));
        }
    }


