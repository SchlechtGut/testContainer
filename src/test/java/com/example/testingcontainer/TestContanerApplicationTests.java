package com.example.testingcontainer;


import com.example.testingcontainer.dao.UserRepository;
import com.example.testingcontainer.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@SpringBootTest
@Testcontainers
class TestContanerApplicationTests {
	@Autowired
	UserRepository userRepository;

	@Container
	static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15.3")
			.withDatabaseName("mydatabase")
			.withUsername("user")
			.withPassword("pass")
			.withExposedPorts(5432);

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@Test
	void test1() {
		User user = new User("New");
		userRepository.save(user);

		Assertions.assertEquals(user, userRepository.findById(user.getId()).get());
	}

}
