package com.jatis.showcase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.showcase.dto.RegisterUserDto;
import com.jatis.showcase.entity.UserEntity;
import com.jatis.showcase.repository.UserRepository;

@Component
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEnc;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByEmail(username).orElse(null);
	}

	@Transactional
	@Override
	public UserEntity signup(RegisterUserDto registration) {
		UserEntity user = new UserEntity();
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEnc.encode(registration.getPassword()));
		user.setBirthDate(registration.getBirthDate());
		
		user.setCreatedBy(user.getEmail());
		user.setAuthorityList("ROLE_USER");
		return userRepo.save(user);
	}
	
	@Transactional
	@Override
	public UserEntity update(RegisterUserDto update) {
		UserEntity saved = userRepo.findByEmail(update.getEmail()).get();
		saved.setBirthDate(update.getBirthDate());
		saved.setPhone(update.getPhone());
		return saved;
	}

	@Override
	public UserEntity getUser(String email) {
		return (UserEntity)this.loadUserByUsername(email);
	}


}
