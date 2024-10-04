package com.brac.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.brac.test.entity.User;
import com.brac.test.repository.UserRepository;
import com.brac.test.service.UserService;

@Controller
public class WebController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/home")
	public String home(Model model) {

		List<User> users = userRepository.findAll();
		model.addAttribute("users", users);

		return "home";
	}

	@PostMapping("/saveUser")
	public String saveUser(@RequestBody User user) {
		// Save the new user to the database
		userService.saveUser(user);

		// Redirect back to the home page
		return "redirect:/home";
	}

	@PutMapping("/editUser")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		// Ensure the user exists before updating
		User existingUser = userService.getUserById(user.getId());
		if (existingUser == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}

		// Update the user information
		existingUser.setUsername(user.getUsername());
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			existingUser.setPassword(user.getPassword()); // Update password only if it's provided
		}
		existingUser.setRole(user.getRole());
		userService.saveUser(existingUser); // Save the updated user

		return ResponseEntity.ok("User updated successfully.");
	}

	@GetMapping("/editUser/{id}")
	public String editUserForm(@PathVariable("id") Long id, Model model) {
		// Add logic for editing a user
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "editUser"; // This should match with editUser.html
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		// Delete the user by ID
		userService.deleteUserById(id);
		return "redirect:/home"; // Redirect back to home page after deletion
	}
}
