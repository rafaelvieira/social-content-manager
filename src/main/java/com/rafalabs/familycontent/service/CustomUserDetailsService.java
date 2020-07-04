package com.rafalabs.familycontent.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafalabs.familycontent.domain.AppUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userService.findByUsername(username);
		if(appUser == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		return new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
	}
	 
	public void signup(AppUser appUser) throws Exception {
		AppUser retrievedAppUser = userService.findByUsername(appUser.getUsername());
	    if(retrievedAppUser != null) {
	    	throw new Exception(
	  	          "User not available:" + appUser.getUsername());
	    }
	    
	    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
	    userService.create(appUser);
	}
}
