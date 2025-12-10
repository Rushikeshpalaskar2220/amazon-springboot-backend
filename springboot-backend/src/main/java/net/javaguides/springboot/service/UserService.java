package net.javaguides.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());

		// Default role assignment if not provided
		if (user.getRole() == null) {
			user.setRole(Role.USER);
		}

		return userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveUser(User user) {
		user.setUpdatedAt(LocalDateTime.now());
		return userRepository.save(user);
	}

}
