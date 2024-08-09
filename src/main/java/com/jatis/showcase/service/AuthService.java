package com.jatis.showcase.service;

import com.jatis.showcase.dto.LoginDto;
import com.jatis.showcase.entity.UserEntity;

public interface AuthService {

	UserEntity authenticate(LoginDto login);

}