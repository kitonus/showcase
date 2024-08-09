package com.jatis.showcase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.jatis.showcase.dto.RegisterUserDto;
import com.jatis.showcase.repository.UserRepository;
import com.jatis.showcase.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("junit")
class ShowcaseApplicationTests {

	@Autowired
	UserRepository repo;
	
	@Autowired
	UserService userService;
	
	@Test
	void testUserRepository() {
		RegisterUserDto user = new RegisterUserDto();
		user.setEmail("test@test.com");
		user.setBirthDate(LocalDate.now());
		user.setPassword("Password123");
		userService.signup(user);
		assertNotNull(repo.findByEmail("test@test.com").orElse(null));
	}

}
