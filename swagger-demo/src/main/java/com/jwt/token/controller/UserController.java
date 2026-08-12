package com.jwt.token.controller;

import com.jwt.token.entity.Users;
import com.jwt.token.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "User Management", description = "API's for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "User Login", description = "Login for user")
    @PostMapping("/login")
    public String login(@RequestBody Users users){
        System.out.println("user: "  + users.toString());
        return userService.verify(users);
    }

    @PostMapping("/user/register")
    public Users register(@RequestBody Users users){
        System.out.println("user: "  + users.toString());
        return userService.registerUser(users);
    }

    @GetMapping("user/findAll")
    public List<Users> findAllUsers(){
        return  userService.findAllUser();
    }

}
