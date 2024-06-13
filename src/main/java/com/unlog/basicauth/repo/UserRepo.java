package com.unlog.basicauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unlog.basicauth.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByUsername(String userName);
}
