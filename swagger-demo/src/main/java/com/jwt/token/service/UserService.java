package com.jwt.token.service;

import com.jwt.token.entity.Users;
import com.jwt.token.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public Users registerUser(Users users) {
           users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

    public List<Users> findAllUser() {
        return userRepository.findAll();
    }

    public String verify(Users users) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUserName(),users.getPassword()));

        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(users.getUserName());
        }

        return "Fail";
    }
}

