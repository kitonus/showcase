package com.jatis.showcase.service;

import com.jatis.showcase.dto.RegisterUserDto;
import com.jatis.showcase.entity.UserEntity;

public interface UserService {

	UserEntity signup(RegisterUserDto registration);

	UserEntity getUser(String email);

	UserEntity update(RegisterUserDto update);
}