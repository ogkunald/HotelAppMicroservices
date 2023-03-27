package com.nastyzera.user.service.UserService.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nastyzera.user.service.UserService.entity.User;
import com.nastyzera.user.service.UserService.exception.ResourceNotFoundException;
import com.nastyzera.user.service.UserService.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        Optional<User> userPresent = userRepository.findById(user.getId_phoneNo());
        if (userPresent.isPresent()) {
            throw new ResourceNotFoundException("User Exist with phone no. "+user.getId_phoneNo());
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId + " NOT FOUND"));

    }

}
