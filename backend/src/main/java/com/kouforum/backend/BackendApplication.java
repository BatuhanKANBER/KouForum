package com.kouforum.backend;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kouforum.backend.dto.ShareCreate;
import com.kouforum.backend.models.Share;
import com.kouforum.backend.models.User;
import com.kouforum.backend.repositories.ShareRepository;
import com.kouforum.backend.repositories.UserRepository;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		System.out.println("Hello World!");
	}

	@Bean
	CommandLineRunner fakeUsers(UserRepository userRepository, ShareRepository shareRepository,
			PasswordEncoder passwordEncoder) {
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
				for (var j = 1; j <= 20; j++) {
					ShareCreate shareCreate = new ShareCreate();
					shareCreate.setContent("Test Paylaşımı " + j + " user(" + i + ")");
					Share share = new Share();
					share.setContent(shareCreate.getContent());
					share.setDate(new Date());
					share.setUser(user);
					shareRepository.save(share);
					System.out.println(share.getContent() + " is created.");
				}
			}
		};
	}
}
