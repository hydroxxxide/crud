package com.example.springintro.service;

import com.example.springintro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
@Service
public class UserService {
    UserRepository repository;
}
