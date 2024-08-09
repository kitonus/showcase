package com.jatis.showcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.showcase.dto.LoginDto;
import com.jatis.showcase.dto.RegisterUserDto;
import com.jatis.showcase.dto.ResponseDto;
import com.jatis.showcase.dto.ResponseVoid;
import com.jatis.showcase.dto.TokenDto;
import com.jatis.showcase.entity.UserEntity;
import com.jatis.showcase.service.AuthService;
import com.jatis.showcase.service.JwtService;
import com.jatis.showcase.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/signup")
	public ResponseVoid signup(@Valid @RequestBody RegisterUserDto register) {
		UserEntity user = userService.signup(register);
		return new ResponseVoid("User "+user.getEmail()+"is successfully saved", true);
	}
	
	@PostMapping("/login")
	public ResponseDto<TokenDto> signin(@RequestBody LoginDto login){
		UserEntity userEnt = authService.authenticate(login);
		return ResponseDto.getInstance(new TokenDto(jwtService.generateToken(userEnt)));
	}
}
