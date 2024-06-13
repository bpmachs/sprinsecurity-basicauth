package com.unlog.basicauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unlog.basicauth.model.User;
import com.unlog.basicauth.model.UserPrincipal;
import com.unlog.basicauth.repo.UserRepo;

@Service
public class UserService implements UserDetailsService{

	
	@Autowired
	private UserRepo userRepo;
	
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public User saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Error 404");
        } else {
            return new UserPrincipal(user);
           // return new org.springframework.security.core.userdetails.User();
        }
	}

}
