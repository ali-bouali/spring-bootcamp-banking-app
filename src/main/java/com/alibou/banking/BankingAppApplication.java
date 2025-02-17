package com.alibou.banking;

import com.alibou.banking.account.AccountRepository;
import com.alibou.banking.account.account;
import com.alibou.banking.role.RoleRepository;
import com.alibou.banking.role.role;
import com.alibou.banking.user.UserRepository;
import com.alibou.banking.user.user;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BankingAppApplication {





	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(RoleRepository roleRepository, AccountRepository accountRepository, UserRepository userRepository) {
		return args -> {
		};
	};
}
