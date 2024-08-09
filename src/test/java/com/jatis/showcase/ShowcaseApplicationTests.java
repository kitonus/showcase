package com.jatis.showcase;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.jatis.showcase.repository.UserRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("junit")
class ShowcaseApplicationTests {

	@Autowired
	UserRepository repo;
	
	@Test
	void testUserRepository() {
		assertNull(repo.findByEmail("test@test.com").orElse(null));
	}

}
