package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.LoginRequest;
import net.javaguides.springboot.dto.LoginResponseDTO;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;
import net.javaguides.springboot.util.JwtUtil;

@RestController
@RequestMapping("/users")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		User savedUser = userService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			User user = userService.findByUsername(loginRequest.getUsername());
			String token = jwtUtil.createToken(user);
			LoginResponseDTO responseDTO = new LoginResponseDTO(token, user.getUsername());
			return ResponseEntity.ok(responseDTO);
			// return ResponseEntity.ok(Collections.singletonMap("token", token));

		} else {
			return ResponseEntity.status(401).body("Invalid credentials");
		}
	}

	@PutMapping("/me")
	public ResponseEntity<?> updateProfile(@RequestBody User updatedUser, Authentication authentication) {
		String currentUsername = authentication.getName();
		User existingUser = userService.findByUsername(currentUsername);

		if (existingUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}

		existingUser.setFirstname(updatedUser.getFirstname());
		existingUser.setLastname(updatedUser.getLastname());
		existingUser.setEmail(updatedUser.getEmail());

		if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
			existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
		}

		User savedUser = userService.saveUser(existingUser);
		return ResponseEntity.ok(savedUser);
	}

	@GetMapping("/me")
	public ResponseEntity<?> getProfile(Authentication authentication) {
		String currentUsername = authentication.getName();
		User user = userService.findByUsername(currentUsername);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		return ResponseEntity.ok(user);
	}

}
