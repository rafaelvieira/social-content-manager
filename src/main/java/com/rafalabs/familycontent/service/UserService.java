package com.rafalabs.familycontent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalabs.familycontent.domain.User;
import com.rafalabs.familycontent.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> list() {
		
		return userRepository.list();
	}

}
