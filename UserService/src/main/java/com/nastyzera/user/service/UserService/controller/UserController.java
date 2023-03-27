package com.nastyzera.user.service.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nastyzera.user.service.UserService.entity.User;
import com.nastyzera.user.service.UserService.service.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userRet = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRet);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        log.info("Get Single User Handler: UserController");

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllusers(){
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }

}
