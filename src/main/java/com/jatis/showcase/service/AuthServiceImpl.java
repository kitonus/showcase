package com.jatis.showcase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.jatis.showcase.dto.LoginDto;
import com.jatis.showcase.entity.UserEntity;
import com.jatis.showcase.repository.UserRepository;

@Component
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Override
	public UserEntity authenticate(LoginDto login) {
		Authentication auth = this.authManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()) );
		return this.userRepo.findByEmail(auth.getName())
				.orElseThrow(()->new BadCredentialsException("Invalid username or password"));
	}
	

}
