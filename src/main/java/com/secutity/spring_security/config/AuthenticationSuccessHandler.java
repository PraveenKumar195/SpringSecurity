package com.secutity.spring_security.config;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        
    	boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        System.out.println("System checked is admin or not");
      
        if (isAdmin) {
        	
        	System.out.println("Login user have admin rights");
           setDefaultTargetUrl("/admin/home");
            System.out.println("ADMIN Default Target method invocked");
       
        } else {
        
        	System.out.println("Login user have only user rights");
            setDefaultTargetUrl("/user/home");
            System.out.println("USER Default Target method invocked ");
        
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}


















/*import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@overide
	public void onAuthenticationSuccess(HttpServletRequest request
										, HttpServletResponse response
										, Authentication authentication) throws ServletException, IOException {
		boolean isAdmin = authentication.getAuthorities().stream()
								.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
		if(isAdmin)
			setDefaultTargetUrl("/admin/home");
		else
			setDefaultTargetUrl("/user/home");
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
}
*/