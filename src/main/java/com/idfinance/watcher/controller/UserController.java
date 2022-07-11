package com.idfinance.watcher.controller;

import com.idfinance.watcher.model.User;
import com.idfinance.watcher.service.implementation.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/crypto")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/notify")
    private ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
    }
}
