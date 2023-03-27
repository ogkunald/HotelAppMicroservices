package com.nastyzera.user.service.UserService.service;

import java.util.List;

import com.nastyzera.user.service.UserService.entity.User;

public interface UserService {
        //create
        User saveUser(User user);

        //get all user
        List<User> getAllUser();
    
        //get single user of given userId
    
        User getUser(String userId);
}
