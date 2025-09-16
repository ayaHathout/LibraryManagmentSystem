package com.example.libraryManagementSystem;

import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.enums.Role;
import com.example.libraryManagementSystem.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	// Create the root admin
	@Bean
	CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if(userRepository.findByUserName("admin").isEmpty()) {
				User admin = User.builder()
						.userName("admin")
						.password(passwordEncoder.encode("admin"))
						.role(Role.ADMIN)
						.isActive(true)
						.email("admin@gmail.com")
						.phone("1234567890")
						.firstName("Root")
						.lastName("Admin")
						.build();
				userRepository.save(admin);
				System.out.println("Root admin created!");
			}
			else {
				System.out.println("Root admin already exists!");
			}
		};
	}

}
