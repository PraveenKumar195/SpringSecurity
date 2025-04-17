package com.secutity.spring_security.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ControllerClass {

	
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}
	
	@GetMapping("/user/home")
	public String getUser() {
		return "user_home";
	}
	
	@GetMapping("/admin/home")
	public String getadmin() {
		return "admin_home";
	}
	
	@GetMapping("/login")
	public String loginforms() {
		return "Custom_login";
	}
	
	
}
