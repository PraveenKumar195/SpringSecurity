package com.secutity.spring_security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.secutity.spring_security.model.MyUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration<DaoAuthencationProvider> {

	@Autowired
	MyUserDetailService userDetailService;
	
	
	//@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		System.out.println("filterchain execution");
		return httpSecurity.csrf(csrf -> csrf.disable())
					.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/home","/register-user").permitAll()
					.requestMatchers("/user/home").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/admin/home").hasRole("ADMIN")
					.anyRequest().authenticated();
					})
					.formLogin(httpFormLogin -> {
						httpFormLogin.loginPage("/login")
						.successHandler(new AuthenticationSuccessHandler())
						.permitAll();
						}).logout(x  -> {
							x.permitAll();
						})
					.build();		
					
	}
	
/*	@Bean
	public UserDetailsService  userDetailsService(){
		
		UserDetails normalUser= User.builder()
									.username("praveen")
									.password("$2a$12$NXU2ufLTPx3KhphqwEAnguNcxmnGj.2HgYRa2cOSuR96fA4TDjDhC")
									.roles("USER")
									.build();
		UserDetails adminUser= User.builder()
									.username("admin")
									.password("$2a$12$NXU2ufLTPx3KhphqwEAnguNcxmnGj.2HgYRa2cOSuR96fA4TDjDhC")
									.roles("USER","ADMIN")
									.build();
			return new InMemoryUserDetailsManager(normalUser, adminUser);
		
	}
	
	*/
	@Bean
	public AuthenticationProvider authencationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(passwordEncoder());
		System.out.println("Authencation provider execution");
		return provider;
	}
	
	
	@Bean
	public UserDetailsService userDetailService() {
		System.out.println("userdetails method execution");
		return userDetailService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("password encder method  execution");
		return new BCryptPasswordEncoder();
	}
	
}
