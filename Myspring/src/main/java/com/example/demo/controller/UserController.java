package com.example.demo.controller;

import java.util.Optional;
import java.util.Map;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:8081/")
public class UserController {
	
	@Autowired
	private UserRepository userrepository;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String,String> loginrequest , HttpSession session){
		String username=loginrequest.get("username");
		String password=loginrequest.get("password");
		User user=userrepository.findByusername(username);
		if(user!=null && user.getPassword().equals(password))
		{
			session.setAttribute("user", user);
			return ResponseEntity.ok(user);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userrepository.findById(id);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		if(userrepository.findByusername(user.getUsername())!=null)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
		}
		if(userrepository.findBypassword(user.getPassword())!=null)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Password already exsits");
		}
		userrepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
	}
	


}
