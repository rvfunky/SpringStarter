package com.raghu.springstarter.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDAO extends UserDetailsService{

	String addUser(String username, String password);
	
	public UserDetails loadUserByUsername(String username);
	
}
