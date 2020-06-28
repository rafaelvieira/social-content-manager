package com.rafalabs.familycontent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafalabs.familycontent.domain.AppUser;
import com.rafalabs.familycontent.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(AppUser appUser) {
		userRepository.create(appUser);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void savePassword(AppUser appUser) {
		userRepository.savePassword(appUser);
	}
	
	public AppUser findByGoogleId(String googleId) {
		
		return userRepository.findByGoogleId(googleId);
	}
	
	public AppUser findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	public List<AppUser> list() {
		
		return userRepository.list();
	}

}
