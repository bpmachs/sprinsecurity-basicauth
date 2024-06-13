package com.unlog.basicauth.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unlog.basicauth.dto.SignInRequest;
import com.unlog.basicauth.model.User;
import com.unlog.basicauth.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	@Autowired
		UserService userService;
	
	 @Autowired
	    private AuthenticationManager authenticationManager;
		
		@PostMapping("/register")
		public ResponseEntity<String> userRegister(@RequestBody User user){
			if(userService.saveUser(user)!=null) {
				return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
				
			} else 
				return new ResponseEntity<>("oops not registered", HttpStatus.OK);
		}
		 @PostMapping("/signin")
		    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
		        try {
		            Authentication authentication = authenticationManager.authenticate(
		                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
		            return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
		        } catch (org.springframework.security.core.AuthenticationException e) {
		            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
		        }
		    }
}
