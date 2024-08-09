package com.jatis.showcase.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	@NotEmpty
	private String username;
	private String password;
}
