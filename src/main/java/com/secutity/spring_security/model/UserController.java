package com.secutity.spring_security.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	MyUserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/register-user")
	public ResponseEntity<MyUser> register(@RequestBody MyUser user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	
		
		
		return ResponseEntity.ok(user);
		
	}
	

}
