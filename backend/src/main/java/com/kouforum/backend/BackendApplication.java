package com.kouforum.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.UserRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println("Hello World!");
	}

	@Bean
	CommandLineRunner fakeUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return (args) -> {
			var userInDB = userRepository.findByEmail("user1@gmail.com");
			if (userInDB != null) {
				return;
			}
			for (var i = 1; i <= 28; i++) {
				User user = new User();
				user.setUsername("fake_user" + i);
				user.setEmail("user" + i + "@gmail.com");
				user.setPassword(passwordEncoder.encode("Password123"));
				user.setIs_active(true);
				userRepository.save(user);
				System.out.println(user.getUsername() + " is created.");
			}
		};
	}
}
