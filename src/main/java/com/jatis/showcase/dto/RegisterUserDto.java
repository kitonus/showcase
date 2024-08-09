package com.jatis.showcase.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
	@Size(min=3, max=100)
	@NotNull
	private String email;
	
	@Size(min=8, max=50)
	@NotNull
	private String password;
	
	@Past
	private LocalDate birthDate;
	
	@Length(max = 20)
	private String phone;
}
