package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.UserDTO;
import com.example.libraryManagementSystem.dtos.UserResponseDTO;
import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.mappers.UserMapper;
import com.example.libraryManagementSystem.repositories.UserRepository;
import com.example.libraryManagementSystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toResponseDTO);
    }

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toResponseDTO(userRepository.save(user));
    }

    @Override
    public Optional<UserResponseDTO> updateUser(Long id, UserDTO updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    if (updatedUser.userName() != null) {
                        user.setUserName(updatedUser.userName());
                    }
                    if (updatedUser.firstName() != null) {
                        user.setFirstName(updatedUser.firstName());
                    }
                    if (updatedUser.lastName() != null) {
                        user.setLastName(updatedUser.lastName());
                    }
                    if (updatedUser.email() != null) {
                        user.setEmail(updatedUser.email());
                    }
                    if (updatedUser.password() != null) {
                        user.setPassword(passwordEncoder.encode(updatedUser.password()));
                    }
                    if (updatedUser.phone() != null) {
                        user.setPhone(updatedUser.phone());
                    }
                    if (updatedUser.role() != null) {
                        user.setRole(updatedUser.role());
                    }
                    System.out.println(user + "\n" + updatedUser);
                    return userRepository.save(user);
                }).map(userMapper::toResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> deleteUser(Long id) {
        Optional<UserResponseDTO> user = userRepository.findById(id).map(userMapper::toResponseDTO);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
        return user;
    }

    @Override
    public Optional<UserResponseDTO> getByUserName(String username) {
        return userRepository.findByUserName(username).map(userMapper::toResponseDTO);
    }
}
