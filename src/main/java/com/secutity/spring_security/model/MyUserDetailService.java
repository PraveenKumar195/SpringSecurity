package com.secutity.spring_security.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	MyUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<MyUser> user = userRepository.findByUsername(username);
		System.out.println("load username method execution");
		if(user.isPresent()) {
			//var userObj = user.get();
			
			return User.builder()
					.username(user.get().getUsername())
					.password(user.get().getPassword())
					.roles(getRoles(user.get()))
					.build();
		}else {
			System.out.println("throw user not found exception ");
			throw new UsernameNotFoundException(username);
		}
		
	}

	private String[] getRoles(MyUser user) {
		
		if(user.getRole()==null)
			return new String[] {"USER"};
		else
			return user.getRole().split(",");
	}
	
	

}
