package com.alibou.banking;

import com.alibou.banking.repositories.UserRepository;
import com.alibou.banking.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);

	}


	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return (args) -> {

			var user = User.builder().email("tayariasma01@gmail.com").firstName("Tayariasma").lastName("Tayariasma").build();
			userRepository.save(user);
		};
	}
}