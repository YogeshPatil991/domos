package com.jwt.token.service;

import com.jwt.token.entity.UserPrincipal;
import com.jwt.token.entity.Users;
import com.jwt.token.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = userRepository.findByUserName(username);
        if(users == null){
            System.out.println("User not found");
            throw  new UsernameNotFoundException("User not found");
        }

        return new  UserPrincipal(users);
    }
}
