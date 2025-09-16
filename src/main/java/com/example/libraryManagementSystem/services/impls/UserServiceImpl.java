package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.repositories.UserRepository;
import com.example.libraryManagementSystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /*@Override
    public User updateUser(User updatedUser) {
        return userRepository.findById(updatedUser.getId())
                .map(user -> {
                    user.setUserName(updatedUser.getUserName());
                    user.setEmail(updatedUser.getEmail());
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setPhone(updatedUser.getPhone());
                    user.setRole(updatedUser.getRole());
                    user.setIsActive(updatedUser.getIsActive());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + updatedUser.getId()));
        return userRepository.save(user);
    } */

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public Optional<User> getByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
