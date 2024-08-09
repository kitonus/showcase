package com.jatis.showcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.showcase.dto.RegisterUserDto;
import com.jatis.showcase.dto.ResponseDto;
import com.jatis.showcase.entity.UserEntity;
import com.jatis.showcase.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/user")
@SecurityRequirement(name = "Bearer token")
@RestController
@Slf4j
public class UserController {
	
	@Autowired
	private UserService service;

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/byemail/{email}")
	public ResponseDto<UserEntity> getByEmail(@PathVariable String email, Authentication whoami) {
		if (log.isDebugEnabled()) {
			log.debug("{}", whoami);
		}
		return ResponseDto.getInstance(service.getUser(email));
	}

	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public ResponseDto<UserEntity> update(@Valid @RequestBody RegisterUserDto update) {
		return ResponseDto.getInstance(service.update(update));
	}
}
