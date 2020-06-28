package com.rafalabs.familycontent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rafalabs.familycontent.domain.AppUser;
import com.rafalabs.familycontent.domain.AuthenticationRequest;
import com.rafalabs.familycontent.domain.AuthenticationResponse;
import com.rafalabs.familycontent.service.CustomUserDetailsService;
import com.rafalabs.familycontent.util.JwtUtil;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		userDetailsService.signup(AppUser.builder()
				.username(authenticationRequest.getUsername())
				.password(authenticationRequest.getPassword())
				.build());

		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), 
					authenticationRequest.getPassword()));
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
