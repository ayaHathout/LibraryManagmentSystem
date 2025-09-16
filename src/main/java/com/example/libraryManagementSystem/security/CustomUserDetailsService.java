package com.example.libraryManagementSystem.security;

import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(currentUser.getUserName())
                .password(currentUser.getPassword()) // already hashed
                .roles(currentUser.getRole().name()) // ADMIN, LIBRARIAN, STAFF
                .build();
    }
}
